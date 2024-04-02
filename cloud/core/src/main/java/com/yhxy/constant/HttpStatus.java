package com.yhxy.constant;

/**
 * @ClassName HttpStatus
 * @Description: HTTP状态码
 * @Author alex
 * @DateTime 2023/3/16 14:24
 * @updateDateTime 2023/3/16 14:24
 * @Version 1.0.0
 **/
public interface HttpStatus {

    /**
     * 操作成功
     */
    int SUCCESS = 200;

    /**
     * 对象创建成功
     */
    int CREATED = 201;

    /**
     * 请求已经被接受
     */
    int ACCEPTED = 202;

    /**
     * 操作已经执行成功，但是没有返回数据
     */
    int NO_CONTENT = 204;

    /**
     * 资源已被移除
     */
    int MOVED_PERM = 301;

    /**
     * 重定向
     */
    int SEE_OTHER = 303;

    /**
     * 资源没有被修改
     */
    int NOT_MODIFIED = 304;

    /**
     * 参数列表错误（缺少，格式不匹配）
     */
    int BAD_REQUEST = 400;

    /**
     * 未授权
     */
    int UNAUTHORIZED = 401;

    /**
     * 访问受限，授权过期
     */
    int FORBIDDEN = 403;

    /**
     * 资源，服务未找到
     */
    int NOT_FOUND = 404;

    /**
     * 不允许的http方法
     */
    int BAD_METHOD = 405;

    /**
     * 服务器无法根据客户端请求的内容特性完成请求
     */
    int Not_Acceptable = 406;

    /**
     * 请求要求代理的身份认证，与401类似，但请求者应当使用代理进行授权
     */
    int Proxy_Authentication_Required = 407;

    /**
     * 服务器等待客户端发送的请求时间过长，超时
     */
    int Request_TimeOut = 408;

    /**
     * 服务器完成客户端的 PUT 请求时可能返回此代码，服务器处理请求时发生了冲突
     */
    int CONFLICT = 409;

    /**
     * 客户端请求的资源已经不存在。410不同于404，如果资源以前有现在被永久删除了可使用410代码，网站设计人员可通过301代码指定资源的新位置
     */
    int Gone = 410;

    /**
     * 服务器无法处理客户端发送的不带Content-Length的请求信息
     */
    int Length_Required = 411;

    /**
     * 客户端请求信息的先决条件错误
     */
    int Precondition_Failed = 412;

    /**
     * 由于请求的实体过大，服务器无法处理，因此拒绝请求。为防止客户端的连续请求，服务器可能会关闭连接。如果只是服务器暂时无法处理，则会包含一个Retry-After的响应信息
     */
    int Request_Entity_Too_Large = 413;

    /**
     * 请求的URI过长（URI通常为网址），服务器无法处理
     */
    int Request_URI_Too_Large = 414;

    /**
     * 服务器无法处理请求附带的媒体格式
     */
    int UNSUPPORTED_TYPE = 415;
    /**
     * 客户端请求的范围无效
     */
    int Requested_range_not_satisfiable = 416;

    /**
     * 服务器无法满足Expect的请求头信息
     */
    int Expectation_Failed = 417;

    /**
     * 系统内部错误
     */
    int ERROR = 500;

    /**
     * 接口未实现
     */
    int NOT_IMPLEMENTED = 501;

    /**
     * 作为网关或者代理工作的服务器尝试执行请求时，从远程服务器接收到了一个无效的响应
     */
    int Bad_Gateway = 502;

    /**
     * 由于超载或系统维护，服务器暂时的无法处理客户端的请求。延时的长度可包含在服务器的Retry-After头信息中
     */
    int Service_Unavailable = 503;

    /**
     * 充当网关或代理的服务器，未及时从远端服务器获取请求
     */
    int Gateway_Time_out = 504;

    /**
     * 服务器不支持请求的HTTP协议的版本，无法完成处理
     */
    int HTTP_Version_not_supported = 505;

}
