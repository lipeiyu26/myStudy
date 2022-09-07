created() {
this.t = window.setInterval(this.getChatUserList, 5000);
},
beforeDestroy() {
clearInterval(this.t);
},
destroyed() {
this.websocketClose();
}
methods: {
// 初始化 websocket 连接
initWebSocket() {
if (typeof WebSocket != "undefined") {
this.supported = "支持 websocket";
} else {
this.supported = "不支持 websocket";
}
//ws地址
var wsAbaseUrl = abaseUrl.substring(abaseUrl.lastIndexOf("/") + 1);
const wsuri = "wss://" + wsAbaseUrl + "/websocket/" + this.$route.query.liveId + "/" + this.type + "/" + this.userId;
this.chatRoomWebsocket = new WebSocket(wsuri);
this.chatRoomWebsocket.onerror = this.websocketOnError;
this.chatRoomWebsocket.onmessage = this.websocketOnMessage;
this.chatRoomWebsocket.onclose = this.websocketOnClose;
},
//连接发生错误的回调方法
websocketOnError() {
console.log("WebSocket 连接发生错误");
},
//接收到消息的回调方法
websocketOnMessage(event) {
console.log(event);
let data = JSON.parse(event.data);
this.msgHeadImageURL = data.chatImage ? data.chatImage : userPhoto;
if (data.chatUser != this.userId) {
this.msgs.push(data);
this.barrageList.push({
id: ++this.currentId,
avatar: this.msgHeadImageURL,
msg: data.chatContent,
barrageStyle: "normal",
time: 5,
type: 0,
position: "bottom"
});
}
},
//连接关闭的回调方法
websocketOnClose(e) {
console.log("WebSocket 连接关闭", e);
},
//关闭 WebSocket 连接
websocketClose() {
this.chatRoomWebsocket.close();
},
//发送弹幕+发送消息
addToList() {
if (this.sendmsg.split(" ").join("").length != 0) {
//获取当前时间
var time = new Date();
this.sendTime =
time.getHours() +
":" +
(time.getMinutes() < 10
? "0" + time.getMinutes()
: time.getMinutes());
let messageData = {
chatContent: this.sendmsg,
chatUser: this.userId,
chatAvatar: this.userInfo.nickName,
chatImage: this.headImageURL,
chatTime: this.sendTime
};
if (this.chatRoomWebsocket.readyState != "1") {
// 如果按下按钮时不是连接状态，重连并等到连接成功再发送消息
this.initWebSocket();
this.chatRoomWebsocket.onopen = () => {
this.chatRoomWebsocket.send(JSON.stringify(messageData));
//发送消息
this.msgs.push({
chatContent: this.sendmsg
});
//弹幕
this.msgHeadImageURL = this.headImageURL
? this.headImageURL
: userPhoto;
this.barrageList.push({
id: ++this.currentId,
avatar: this.msgHeadImageURL,
msg: this.sendmsg,
barrageStyle: "normal",
time: 5,
type: 0,
position: "bottom"
});
this.sendmsg = "";
};
} else {
this.chatRoomWebsocket.send(JSON.stringify(messageData));
//发送消息
this.msgs.push({
chatContent: this.sendmsg
});
//弹幕
this.msgHeadImageURL = this.headImageURL
? this.headImageURL
: userPhoto;
this.barrageList.push({
id: ++this.currentId,
avatar: this.msgHeadImageURL,
msg: this.sendmsg,
barrageStyle: "normal",
time: 5,
type: 0,
position: "bottom"
});
this.sendmsg = "";
}
}
},
//定时器获取总人数以及列表
getChatUserList() {
let data = {
sid: this.$route.query.liveId,
type: 1
};
onlineList(data).then(res => {
if (res.data.state == 200) {
this.onlineNumber = res.data.data.count;
this.onlineUserList = res.data.data.userList;
}
});
},
},
