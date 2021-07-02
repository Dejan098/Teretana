$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8081/shcedule/getreserveschedule",
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
                row += "<td>" + data[i]['price'] + "</td>";
                row += "<td>" + data[i]['beginDate'] + "</td>";
                row += "<td>" + data[i]['training']['name'] + "</td>";
                var btn = "<button class='btnCancelAppointment' id = " + data[i]['id'] + ">Otkazi termin</button>";


                row += "<td>" + btn + "</td>";




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
        url: "http://localhost:8081/shcedule/cancelappointment",
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
            window.location.href = "memberhomepage.html";
        },
        error: function (error) {
            alert(error);
        }
    });

});