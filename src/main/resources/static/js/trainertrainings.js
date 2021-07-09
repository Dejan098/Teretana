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
        url: "http://localhost:8081/training/getall",
        dataType: "json",
        success: function (data) {
            console.log("SUCCESS : ", data);
            for (i = 0; i < data.length; i++) {
                var row = "<tr>";
                row += "<td>" + data[i]['id'] + "</td>";
                row += "<td>" + data[i]['name'] + "</td>";
                row += "<td>" + data[i]['description'] + "</td>";
                row += "<td>" + data[i]['type'] + "</td>";
                row += "<td>" + data[i]['duration'] + "</td>";



                $('#training').append(row);
            }
        },
        error: function (data) {
            console.log("ERROR : ", data);
        }
    });
});

$(document).on('click', '#btnSearchTraining', function () {

    var kriterijum = $("#kriterijum").val();
    var naziv=$('#naziv').val();
    var tip=$('#tip').val();
    var opis=$('#opis').val();
    var cena=$('#cena').val();
    var vreme=$('#vreme').val();
    var uloga=$('#ulogaulogovanogkorisnika')

    var myJSON = formToJSON(kriterijum,naziv,tip,opis,cena,vreme)

    $('#training tbody').empty();
    $.ajax({
        type: "POST",
        url: "http://localhost:8081/training/search",
        dataType: "json",
        contentType: "application/json",
        data: myJSON,
        beforeSend: function (xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            console.log("SUCCESS: ", data);


            for (i = 0; i < data.length; i++) {
                var row = "<tr>";
                row += "<td>" + data[i]['id'] + "</td>";
                row += "<td>" + data[i]['name'] + "</td>";
                row += "<td>" + data[i]['description'] + "</td>";
                row += "<td>" + data[i]['type'] + "</td>";
                row += "<td>" + data[i]['duration'] + "</td>";





                /*var btn = "<button class='pharmacies' id = " + data[i]['id'] + ">Cene po apotekama</button>";
                var btn1 = "<button class='specs' id = " + data[i]['id'] + ">Specifikacija</button>";

                row += "<td>" + btn + "</td>";
                row += "<td>" + btn1 + "</td>";*/
                $('#training').append(row);
            }

        },
        error: function (data) {
            console.log("ERROR: ", data);
            window.location.href="error.html";
        }
    });

});
$(document).on('click', '#btnSortbyDatum', function () {    // Čeka se trenutak kada je DOM(Document Object Model) učitan da bi JS mogao sa njim da manipuliše.
    // ajax poziv
    $('#schedule tbody').empty();

    $.ajax({
        type: "GET",                                                // HTTP metoda
        url: "http://localhost:8081/shcedule/sortbyvreme",                  // URL koji se gađa
        dataType: "json",                                           // tip povratne vrednosti
        success: function (data) {
            console.log("SUCCESS : ", data);                        // ispisujemo u konzoli povratnu vrednost
            for (i = 0; i < data.length; i++) {                     // prolazimo kroz listu svih zaposlenih
                var row = "<tr>";// kreiramo red za tabelu
                row += "<td>" + data[i]['id'] + "</td>";
                row += "<td>" + data[i]['price'] + "</td>";
                row += "<td>" + data[i]['beginDate'] + "</td>";
                row += "<td>" + data[i]['training']['name'] + "</td>";

                $('#schedule').append(row);                        // ubacujemo kreirani red u tabelu čiji je id = employees
            }
        },
        error: function (data) {
            console.log("ERROR : ", data);
        }
    });
});
$(document).on('click', '#btnSortbyCena', function () {    // Čeka se trenutak kada je DOM(Document Object Model) učitan da bi JS mogao sa njim da manipuliše.
    // ajax poziv
    $('#schedule tbody').empty();

    $.ajax({
        type: "GET",                                                // HTTP metoda
        url: "http://localhost:8081/shcedule/sortbycena",                  // URL koji se gađa
        dataType: "json",                                           // tip povratne vrednosti
        success: function (data) {
            console.log("SUCCESS : ", data);                        // ispisujemo u konzoli povratnu vrednost
            for (i = 0; i < data.length; i++) {                     // prolazimo kroz listu svih zaposlenih
                var row = "<tr>";// kreiramo red za tabelu
                row += "<td>" + data[i]['id'] + "</td>";
                row += "<td>" + data[i]['price'] + "</td>";
                row += "<td>" + data[i]['beginDate'] + "</td>";
                row += "<td>" + data[i]['training']['name'] + "</td>";

                $('#schedule').append(row);                        // ubacujemo kreirani red u tabelu čiji je id = employees
            }
        },
        error: function (data) {
            console.log("ERROR : ", data);
        }
    });
});
function formToJSON(kriterijum,naziv,tip,opis,cena,vreme) {
    return JSON.stringify(
        {
            "kriterijum": kriterijum,
            "naziv":naziv,
            "tip":tip,
            "opis":opis,
            "cena":cena,
            "vreme":vreme

        }
    );
}

$(document).on('click', '#btnSearchTraining2', function () {


    var name=$('#name').val();
    var description=$('#description').val();
    var type=$('#type').val();
    var duration=$('#duration').val();


    var myJSON = formToJSON4(name,description,type,duration)

    $.ajax({
        type: "POST",
        url: "http://localhost:8081/training/createtrening",
        dataType: "json",
        contentType: "application/json",
        data: myJSON,
        beforeSend: function (xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            console.log("SUCCESS: ", data);
            window.location.href="trainerhomepage.html";

        },
        error: function (data) {
            console.log("ERROR: ", data);
            window.location.href="error.html";
        }
    });

});

function formToJSON4(name,description,type,duration) {
    return JSON.stringify(
        {
            "name": name,
            "description":description,
            "type":type,
            "duration":duration


        }
    );
}