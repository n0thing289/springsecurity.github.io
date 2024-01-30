//保证页面渲染好了， 元素可获取
window.onload = function(){

    //获取input username
    let username = $(".username")
    username.keyup(()=>{
        console.log(username.val())
    })

    //获取input password
    let password = $(".password")
    password.keyup(()=>{
        console.log(password.val())
    })

    //获取登录按钮
    let loginBtn = $(".loginBtn")
    loginBtn.click(()=>{
        //把数据封装成json
        let json = {"username":username.val(), "password":password.val()}
        //把数据发送给服务器
        axios.post("/api/login", json).then((res)=>{
            //打印响应体信息
            console.log(res.data)
            if(res.data.code === "verify_ok"){
                //模拟vue把token存储到了localStorage
                localStorage.setItem("token", res.data.data)
                //跳转index页面
                // window.location.href = "/"
                alert("登录成功token: " + localStorage.getItem("token"))
                alert("已存储在localStorage!")
            }else if(res.data.code === "verify_err"){
                alert("login.js: 登录失败, 请检查用户名或者密码！")
            }else{

            }
        })
    })



}