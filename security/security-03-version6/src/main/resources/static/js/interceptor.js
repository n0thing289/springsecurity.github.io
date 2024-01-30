//拦截所有请求, 请求拦截器
axios.interceptors.request.use(
    config => {
        console.log(config) // 该处可以将config打印出来看一下，该部分将发送给后端（server端）
        //统一发送token
        config.headers.Authorization = localStorage.getItem("token")
        console.log("axios拦截到请求: " + config.url)
        console.log("axios拦截器头部添加token: " + config.headers.Authorization)

        // return config // 对config处理完后返回，下一步将向后端发送请求
        return config
    },
    error => { // 当发生错误时，执行该部分代码
        // console.log(error) // 调试用
        return Promise.reject(error)
    }
)

//拦截所有响应, 响应拦截器
// axios.interceptors.response.use(res => {
//     // 请求成功对响应数据做处理
//     // console.log("axios拦截到响应: "+ res)
//     // 该返回的数据则是axios.then(res)中接收的数据
//     return res
// }, err => {
//     // 在请求错误时要做的事儿
//
//     // 该返回的数据则是axios.catch(err)中接收的数据
//     return Promise.reject(err)
// })


/**
 * 作者：我彦祖不会秃
 * 链接：https://juejin.cn/post/7100470316857557006
 * 来源：稀土掘金
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */