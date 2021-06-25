$(document).on("submit", "form", function(event){

    // da se izbegne izvrsavanja pravog submita forme
    event.preventDefault();

    var email = $("#email").val();
    var password = $("#password").val();

    var myJSON = formToJSON(email, password);

    $.ajax({
        type: "POST",
        url: "http://localhost:8081/users",
        dataType: "json",
        contentType: "application/json",
        data: myJSON,
        success: function (data) {
            // data = ulogovani korisnik koji je vratila metoda iz kontrolera
            // mozemo tu vrednost da ispisemo u konzoli
            console.log(data);

            alert(email + " je uspešno ulogovan");
            // postavljamo ulogovanog korisnika na localStorage
            // na isti nacin moze da se postavi i email, username itd.
            localStorage.setItem('uloga', data['rola']);
            // kasnije u bilo kom js fajlu moze da se dobavi ulogovani korisnik ili njegova uloga na sledeci nacin:
            var ulogaUlogovanogKorisnika = localStorage.getItem('uloga');
            // ispisujemo ulogu u konzoli da bismo potvrdili da je sve u redu
            console.log("Ovo je postavljena uloga ulogovanog korisnika:" + ulogaUlogovanogKorisnika);
            alert("Uloga " + email + " je " + ulogaUlogovanogKorisnika );
            // redirektujemo se na neku drugu stranicu
            window.location.href = "homepage.html";
        },
        error: function (data) {
            console.log(data);
            alert("Greska");
        }
    });
});

function formToJSON(email, password) {
    return JSON.stringify(
        {
            "email": email,
            "password": password

        }
    );
}