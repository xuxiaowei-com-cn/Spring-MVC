/**
 * 注销登录
 */
function logout(url) {

    const header = $("meta[name='_csrf_header']").attr("content");
    const token = $("meta[name='_csrf']").attr("content");

    console.log(header, token);

    $.ajax({
        url: url,
        method: "post",
        dataType: 'json',
        data: {},
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function (result) {
            console.log("result", result);

            const code = result.code;
            const msg = result.msg;

            if (code === 0) {
                alert(msg);
                // location.reload();
                location.href = ""
            } else if (code === 1) {
                alert(msg);
            } else {
                alert("响应 Code 异常：" + result);
            }

        },
        error: function (error) {
            console.log("error", error);
        }
    })

}
