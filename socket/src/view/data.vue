<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Index</title>
  <script src="static/js/vue.js"></script>
  <script src="static/js/axios.min.js"></script>
</head>
<body>
<div id="get_host_data">
  <button @click="GetHostData">获取数据</button>
  <table class="table table-bordered table-hover table-striped">
    <thead>
    <tr>
      <td>ID</td>
    </tr>
    </thead>
    <tbody>
    <tr v-for="(item, index) of host_list" :key="item.id">
      <td>{{ item.id }}</td>
    </tr>
    </tbody>
  </table>
</div>
<script>
  new Vue({
    el:"#get_host_data",
    data: {
      host_list: []
    },
    methods:{
      GetHostData:function(){
        var url = "http://127.0.0.1:8082/super_cmdb/serverhost_list/";
        axios.get(url).then(response => {
          if (response.data.status==0) {
          this.host_list = response.data.message;
        } else {
          console.error("获取机器列表失败")
        }
      })
      }
    },
    mounted:function () {   //自动触发写入的函数
      this.GetHostData();
    }
  })
</script>
</body>
</html>
