$(document).on("submit", "form", function(event){

    // da se izbegne izvrsavanja pravog submita forme
    event.preventDefault();

    var email = $("#email").val();
    var password = $("#password").val();
    var username=$("#username").val();
    var surname =$("#surname").val();
    var phoneNumber =$("#phoneNumber").val();
    var birthDate =$("#birthDate").val();
    var namee =$("#namee").val();
    var fitnescentar =$("#fitnescentar").val();
    var myJSON = formToJSON(email, password,username,namee,surname,phoneNumber,birthDate,fitnescentar);

    $.ajax({
        type: "POST",
        url: "http://localhost:8081/auth/signuptrenerenabled",
        dataType: "json",
        contentType: "application/json",
        data: myJSON,
        success: function () {
            // data = ulogovani korisnik koji je vratila metoda iz kontrolera
            // mozemo tu vrednost da ispisemo u konzoli
            alert("Uspesna registracija");

        },
        error: function (error) {

            alert("Greska");
        }
    });
});

function formToJSON(email, password,username,namee,surname,phoneNumber,birthDate,fitnescentar) {
    return JSON.stringify(
        {
            "email": email,
            "password": password,
            "username":username,
            "namee":namee,
            "surname":surname,
            "phoneNumber": phoneNumber,
            "birthDate" :birthDate,
            "fitnescentar":fitnescentar
        }
    );
}
$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8081/auth/gettreneridisabled",
        dataType: "json",
        success: function (data) {
            console.log("SUCCESS : ", data);
            for (i = 0; i < data.length; i++) {
                var row = "<tr>";
                row += "<td>" + data[i]['id'] + "</td>";
                row += "<td>" + data[i]['namee'] + "</td>";
                row += "<td>" + data[i]['korisnickoime'] + "</td>";
                row += "<td>" + data[i]['surname']+ "</td>";
                row += "<td>" + data[i]['phoneNumber']+ "</td>";
                row += "<td>" + data[i]['email']+ "</td>";
                row += "<td>" + data[i]['birthDate']+ "</td>";
                row += "<td>" + data[i]['enabled']+ "</td>";

                var btn = "<button class='btnCancelAppointment' id = " + data[i]['id'] + ">Odobri trenera</button>";



                row += "<td>" + btn + "</td>";






                $('#schedule').append(row);
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
        url: "http://localhost:8081/auth/gettreneri",
        dataType: "json",
        success: function (data) {
            console.log("SUCCESS : ", data);
            for (i = 0; i < data.length; i++) {
                var row = "<tr>";
                row += "<td>" + data[i]['id'] + "</td>";
                row += "<td>" + data[i]['namee'] + "</td>";
                row += "<td>" + data[i]['korisnickoime'] + "</td>";
                row += "<td>" + data[i]['surname']+ "</td>";
                row += "<td>" + data[i]['phoneNumber']+ "</td>";
                row += "<td>" + data[i]['email']+ "</td>";
                row += "<td>" + data[i]['birthDate']+ "</td>";
                row += "<td>" + data[i]['enabled']+ "</td>";

                var btn = "<button class='btnCancelAppointment2' id = " + data[i]['id'] + ">izbrisi trenera</button>";



                row += "<td>" + btn + "</td>";






                $('#schedule2').append(row);
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
        url: "http://localhost:8081/auth/odobritrenera",
        dataType: "json",
        contentType: "application/json",
        data:id,
        success: function () {
            alert("success");
            window.location.href = "adminhomepage.html";
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
        url: "http://localhost:8081/auth/izbrisi",
        dataType: "json",
        contentType: "application/json",
        data:id,
        success: function () {
            alert("success");
            window.location.href = "adminhomepage.html";
        },
        error: function (error) {
            alert(error);
        }
    });

});