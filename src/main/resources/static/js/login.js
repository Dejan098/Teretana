$(document).ready(function () {

    $('#btnLogin').click(function () {

    	event.preventDefault();
        
	    var email = $("#email").val();
	    var password = $("#password").val();
	
	    var myJSON = formToJSON(email, password);
	
	    $.ajax({
	        type: "POST",
	        url: "http://localhost:8081/auth/login",
	        dataType: "json",
	        contentType: "application/json",
	        data: myJSON,
	        success: function (data) {

				if(data['enabled']===false){
					alert("Nije aktiviran nalog")
					window.location.href = "index.html";
				}

	            console.log(data);
	
	            alert(email + " je uspešno ulogovan");

				localStorage.setItem('token', data['accessToken']);
				localStorage.setItem('role', data['role']);
				if (data['role']==='member'){
					window.location.href = "memberhomepage.html";
				}

				if (data['role']==='trainer'){
					window.location.href = "trainerhomepage.html";
				}


	        },
	        error: function (data) {
	            console.log(data);
	            alert("Greska");
	        }
	    });
        
    })
})

function formToJSON(email, password) {
    return JSON.stringify(
        {
            "email": email,
            "password": password
        }
    );
}