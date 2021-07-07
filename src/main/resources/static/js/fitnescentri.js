$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8081/fitnesscenter/getfitnescenter",
        dataType: "json",
        beforeSend: function(xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            console.log("SUCCESS : ", data);
            for (i = 0; i < data.length; i++) {
                var row = "<tr>";
                row += "<td>" + data[i]['id'] + "</td>";
                row += "<td>" + data[i]['name'] + "</td>";
                row += "<td>" + data[i]['address'] + "</td>";
                row += "<td>" + data[i]['phone']+ "</td>";
                row += "<td>" + data[i]['email']+ "</td>";
                var btn = "<button class='btnCancelAppointment' id = " + data[i]['id'] + ">izbrisi centar</button>";
                var btn2 = "<button class='btnIzmenilAppointment' id = " + data[i]['id'] + ">izmeni</button>";
                var btn3 = "<button class='btndodajsalu' id = " + data[i]['id'] + ">dodajsalu</button>";


                row += "<td>" + btn + "</td>";
                row += "<td>" + btn2 + "</td>";
                row += "<td>" + btn3 + "</td>";





                $('#schedule').append(row);
            }
        },
        error: function (data) {
            console.log("ERROR : ", data);
        }
    });
});
$(document).on('click', '.btnCancelAppointment', function(){
    var id=this.id;
    $.ajax({
        type: "POST",
        url: "http://localhost:8081/fitnesscenter/deletefitnescenter",
        dataType: "json",
        contentType: "application/json",
        data:id,
        beforeSend: function (xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function () {
            alert("success");
            window.location.href = "adminhomepage.html";
        },
        error: function (error) {
            alert(error);
        }
    });

});

$(document).on('click', '#btnSearchTraining', function(){
    var id=this.id;
    var name = $("#name").val();
    var address=$('#address').val();
    var phone=$('#phone').val();
    var email=$('#email').val();
    var myJSON = formToJSON(name,address,phone,email)
    $.ajax({
        type: "POST",
        url: "http://localhost:8081/fitnesscenter/creatfitnescenter",
        dataType: "json",
        contentType: "application/json",
        data:myJSON,
        beforeSend: function (xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function () {
            alert("success");
            window.location.href = "adminhomepage.html";

        },
        error: function (error) {
            alert(error);

        }

    });

});
$(document).on('click', '.btnIzmenilAppointment', function(){
    var id=this.id;
    var name = $("#name").val();
    var address=$('#address').val();
    var phone=$('#phone').val();
    var email=$('#email').val();
    var myJSON = formToJSON2(id,name,address,phone,email)

    $.ajax({
        type: "POST",
        url: "http://localhost:8081/fitnesscenter/izmenicentar",
        dataType: "json",
        contentType: "application/json",
        data:myJSON,
        beforeSend: function (xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function () {
            alert("success");
            window.location.href = "adminhomepage.html";
        },
        error: function (error) {
            alert(error);
        }
    });

});

function formToJSON(name,address,phone,email) {
    return JSON.stringify(
        {
            "name": name,
            "address":address,
            "phone":phone,
            "email":email

        }
    );
}

function formToJSON2(id,name,address,phone,email) {
    return JSON.stringify(
        {
            "id":id,
            "name": name,
            "address":address,
            "phone":phone,
            "email":email

        }
    );
}
