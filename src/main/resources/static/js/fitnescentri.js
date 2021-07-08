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
                var btn3 = "<button class='btndodajsalu' id = " + data[i]['id'] + ">prikazi sale</button>";


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
$(document).on('click', '.btndodajsalu', function(){
    $('#schedule2 tbody').empty();
    var id=this.id;
    $.ajax({
        type: "POST",
        url: "http://localhost:8081/fitnesscenter/gethalls",
        dataType: "json",
        contentType: "application/json",
        data:id,
        beforeSend: function (xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            console.log("SUCCESS : ", data);
            for (i = 0; i < data.length; i++) {
                var row = "<tr>";
                row += "<td>" + data[i]['id'] + "</td>";
                row += "<td>" + data[i]['capacity'] + "</td>";
                row += "<td>" + data[i]['label'] + "</td>";
                var btn = "<button class='btnCancelAppointment2' id = " + data[i]['id'] + ">izbrisi salu</button>";
                var btn2 = "<button class='btnIzmenilAppointment2' id = " + data[i]['id'] + ">izmeni</button>";



                row += "<td>" + btn + "</td>";
                row += "<td>" + btn2 + "</td>";





                $('#schedule2').append(row);
            }
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
function formToJSON3(id,capacity,label,fitnessala) {
    return JSON.stringify(
        {
            "id":id,
            "capacity": capacity,
            "label":label,
            "fitnessala":fitnessala


        }
    );
}
function formToJSON4(capacity,label,fitnessala) {
    return JSON.stringify(
        {

            "capacity": capacity,
            "label":label,
            "fitnessala":fitnessala


        }
    );
}

$(document).on('click', '#btnSearchTraining2', function(){
    var id=this.id;
    var capacity = $("#capacity").val();
    var label=$('#label').val();
    var fitnessala=$('#fitnessala').val();
    var myJSON = formToJSON4(capacity,label,fitnessala)
    $.ajax({
        type: "POST",
        url: "http://localhost:8081/fitnesscenter/createhalls",
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


        },
        error: function (error) {
            alert(error);

        }

    });

});

$(document).on('click', '.btnIzmenilAppointment2', function(){
    var id=this.id;
    var capacity = $("#capacity").val();
    var label=$('#label').val();
    var fitnessala=$('#fitnessala').val();
    var myJSON = formToJSON3(id,capacity,label,fitnessala)
    $.ajax({
        type: "POST",
        url: "http://localhost:8081/fitnesscenter/izmenihalls",
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


        },
        error: function (error) {
            alert(error);

        }

    });

});

$(document).on('click', '.btnCancelAppointment2', function(){
    var id=this.id;
    $.ajax({
        type: "POST",
        url: "http://localhost:8081/fitnesscenter/obrisisalu",
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

        },
        error: function (error) {
            alert(error);
        }
    });

});
