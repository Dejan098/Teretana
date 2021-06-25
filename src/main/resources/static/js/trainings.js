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