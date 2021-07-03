$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8081/training/donetrainings",
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
                row += "<td>" + data[i]['description'] + "</td>";
                row += "<td>" + data[i]['type'] + "</td>";
                row += "<td>" + data[i]['duration'] + "</td>";
                //row += "<td>" + data[i]['ocena']['ocena'] + "</td>";



                $('#training').append(row);

            }


        },
        error: function (data) {
            console.log("ERROR : ", data);
        }
    });
});


$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8081/training/donetrainingsnoocena",
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
                row += "<td>" + data[i]['description'] + "</td>";
                row += "<td>" + data[i]['type'] + "</td>";
                row += "<td>" + data[i]['duration'] + "</td>";
                //row += "<td>" + data[i]['ocena']['ocena'] + "</td>";
                var btn = "<button class='btnCancelAppointment' id = " + data[i]['id'] + ">dodeli ocenu</button>";


                row += "<td>" + btn + "</td>";



                $('#training2').append(row);

            }


        },
        error: function (data) {
            console.log("ERROR : ", data);
        }
    });
});

$(document).on('click', '.btnCancelAppointment', function(){
    var id=this.id;
    var ocena= $("#ocena").val();
    var newUserJSON = formToJSON(id,ocena);
    $.ajax({
        type: "POST",
        url: "http://localhost:8081/training/ocenitrenings",
        dataType: "json",
        contentType: "application/json",
        data:newUserJSON,
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

function formToJSON(id,ocena) {
    return JSON.stringify(
        {
            "id": id,
            "ocena": ocena
        }
    );
};


$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8081/training/donetrainingsocenjeni",
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
                row += "<td>" + data[i]['description'] + "</td>";
                row += "<td>" + data[i]['type'] + "</td>";
                row += "<td>" + data[i]['duration'] + "</td>";
                row += "<td>" + data[i]['ocena'] + "</td>";



                $('#training3').append(row);

            }


        },
        error: function (data) {
            console.log("ERROR : ", data);
        }
    });
});