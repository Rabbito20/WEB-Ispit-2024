function registerKorisnika() {
    const email = document.getElementById("regEmailInput").value;
    const ime = document.getElementById("regImeInput").value;
    const prezime = document.getElementById("regPrezimeInput").value;
    const password = document.getElementById("regPasswordInput").value;
    const tipKorisnika = document.getElementById("regTipInput").value;
    const status = document.getElementById("regStatus").value;

    const user = {
        email: email,
        ime: ime,
        prezime: prezime,
        tipKorisnika: tipKorisnika,
        status: status,
        password: password
    };

    fetch('api/user/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
        .then(response => response.json())
        .then(data => {
            document.getElementById('responsePoruka')
                .innerText = 'Korisnik uspesno registrovan!';
        })
        .catch((error) => {
            document.getElementById('responsePoruka').innerText = 'Greska' + error.message;
        });
}

function loginKorisnika() {
    const email = document.getElementById("loginEmailInput").value;
    const password = document.getElementById("loginPasswordInput").value;

    const credentials = {
        email: email,
        password: password
    };

    fetch('api/user/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials)
    }).then(response => response.json())
        .then(data => {
            if (data.success) {
                document.getElementById('responsePoruka')
                    .innerText = 'Login uspesan!' + email + ' Dobrodosli!';

                //  todo:   REDIRECT
            } else {
                document.getElementById('responsePoruka')
                    .innerText = 'Pogresan email ili password!';
            }
        })
        .catch((error) => {
            document.getElementById('responsePoruka')
                .innerText = 'Greska:' + error.message;
        });
}

