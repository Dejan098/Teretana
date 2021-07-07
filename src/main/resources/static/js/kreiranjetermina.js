$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8081/shcedule/schedules",
        dataType: "json",
        success: function (data) {
            console.log("SUCCESS : ", data);
            for (i = 0; i < data.length; i++) {
                var row = "<tr>";
                row += "<td>" + data[i]['id'] + "</td>";
                row += "<td>" + data[i]['price'] + "</td>";
                row += "<td>" + data[i]['beginDate'] + "</td>";
                row += "<td>" + data[i]['training']['name'] + "</td>";
                var btn = "<button class='btnCancelAppointment' id = " + data[i]['id'] + ">izbrisi termin</button>";
                var btn2 = "<button class='btnIzmenilAppointment' id = " + data[i]['id'] + ">izmeni</button>";


                row += "<td>" + btn + "</td>";
                row += "<td>" + btn2 + "</td>";





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
        url: "http://localhost:8081/shcedule/deleteschedule",
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
            window.location.href = "trainerhomepage.html";
        },
        error: function (error) {
            alert(error);
        }
    });

});

$(document).on('click', '.btnIzmenilAppointment', function(){
    var id=this.id;
    var price = $("#price").val();
    var sala=$('#sala').val();
    var beginDate=$('#beginDate').val();
    var training=$('#training').val();
    var myJSON = formToJSON2(id,price,sala,beginDate,training)
    $.ajax({
        type: "POST",
        url: "http://localhost:8081/shcedule/izmenieschedule",
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
            window.location.href = "trainerhomepage.html";
        },
        error: function (error) {
            alert(error);
        }
    });

});

$(document).on('click', '#btnSearchTraining', function(){
    var id=this.id;
    var price = $("#price").val();
    var sala=$('#sala').val();
    var beginDate=$('#beginDate').val();
    var training=$('#training').val();
    var myJSON = formToJSON(price,sala,beginDate,training)
    $.ajax({
        type: "POST",
        url: "http://localhost:8081/shcedule/createschedule",
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
            window.location.href = "trainerhomepage.html";

        },
        error: function (error) {
            alert(error);
            window.location.href = "trainerhomepage.html";
        }

    });

});

function formToJSON(price,sala,beginDate,training) {
    return JSON.stringify(
        {
            "price": price,
            "sala":sala,
            "beginDate":beginDate,
            "training":training

        }
    );
}

function formToJSON2(id,price,sala,beginDate,training) {
    return JSON.stringify(
        {
            "id":id,
            "price": price,
            "sala":sala,
            "beginDate":beginDate,
            "training":training

        }
    );
}