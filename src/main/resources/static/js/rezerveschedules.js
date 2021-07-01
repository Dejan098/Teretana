$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8081/shcedule/getreserveschedule",
        dataType: "json",
        success: function (data) {
            console.log("SUCCESS : ", data);
            for (i = 0; i < data.length; i++) {
                var row = "<tr>";
                row += "<td>" + data[i]['id'] + "</td>";
                row += "<td>" + data[i]['price'] + "</td>";
                row += "<td>" + data[i]['beginDate'] + "</td>";
                row += "<td>" + data[i]['training']['name'] + "</td>";
                var btn = "<button class='btnRegisterAppointment' id = " + data[i]['id'] + ">Reserve Schedule</button>";


                row += "<td>" + btn + "</td>";





                $('#schedule').append(row);
            }
        },
        error: function (data) {
            console.log("ERROR : ", data);
        }
    });
});