let editBtn = document.getElementById("edit");
let deleteBtn = document.getElementById("delete");
let uploadBtn = document.getElementById("avatar");
var itemId = null;

var myModal = document.getElementById('createModal');

myModal.addEventListener('hide.bs.modal', function () {
    clearFields();
});

myModal.addEventListener('shown.bs.modal', function () {
    if (itemId != null) {
        let xhr = new XMLHttpRequest();
        xhr.open('GET', '/api/user?userId='+itemId, false);
        xhr.setRequestHeader('Content-type', 'application/json');
        xhr.send();

        if (xhr.status == 200) {
            let response = JSON.parse(xhr.responseText);
            document.getElementById("fio").value = response.fio;
            document.getElementById("birthday").value = response.birthday.substr(0, 10);
            document.getElementById("phone").value = response.phone;
            document.getElementById("email").value = response.email;
            document.getElementById("password").value = response.password;
            document.getElementById("avatar").value = response.avatar;
            document.getElementById("activated").checked = response.activated;
            document.getElementById("role").value = response.role.id;
            console.log(response.birthday.substr(0, 10));
        }
    } else {
        clearFields();
    }
});

uploadBtn.addEventListener("change", handleFiles, false);
function handleFiles() {
    console.log(this.files);
    const fileList = this.files;
}

function selectRow (e) {
    Array.from(document.getElementsByTagName("tr")).forEach((el) => {
        el.style.border = "";
    });

    if (e != null) {
        if (e.target.parentElement.getAttribute("value") == itemId) {
            e.target.parentElement.style.border = "";
            hideButtons();
        } else {
            e.target.parentElement.style.border = "solid 2px black";
            showButtons();
            itemId = e.target.parentElement.getAttribute("value");
            return;
        }
    }
    itemId = null;
}

function save () {
    let fio = document.getElementById("fio").value;
    let birthday = document.getElementById("birthday").value;
    let phone = document.getElementById("phone").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let avatar = document.getElementById("avatar").value;
    let activated = document.getElementById("activated").checked;
    let role = document.getElementById("role").value;

    let request = {
        "id": itemId,
        "fio": fio,
        "birthday": birthday,
        "phone": phone,
        "email": email,
        "password": password,
        "avatar": avatar,
        "activated": activated,
        "role": {
            "id": role
        },
    };

    let xhr = new XMLHttpRequest();
    xhr.open('POST', '/api/user', false);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send(JSON.stringify(request));

    if (xhr.status == 200) {
        itemId = null;
        hideButtons();
        location.reload();
    }
}

function remove () {
     if (itemId != null) {
        let xhr = new XMLHttpRequest();
        xhr.open('DELETE', '/api/user?userId='+itemId, false);
        xhr.setRequestHeader('Content-type', 'application/json');
        xhr.send();

        if (xhr.status == 200) {
            itemId = null;
            hideButtons();
            location.reload();
        }
    }
}

function hideButtons() {
    editBtn.setAttribute('disabled', '');
    deleteBtn.setAttribute('disabled', '');
}

function showButtons() {
    editBtn.removeAttribute('disabled');
    deleteBtn.removeAttribute('disabled');
}

function clearFields() {
    document.getElementById("fio").value = "";
    document.getElementById("birthday").value = "";
    document.getElementById("phone").value = "";
    document.getElementById("email").value = "";
    document.getElementById("password").value = "";
    document.getElementById("avatar").value = "";
    document.getElementById("activated").checked = false;
    document.getElementById("role").value = "";
}