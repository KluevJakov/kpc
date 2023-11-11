let editBtn = document.getElementById("edit");
let deleteBtn = document.getElementById("delete");
let qrBtn = document.getElementById("qr");
let uploadBtn = document.getElementById("avatar");
let photo = document.getElementById("photo");
var itemId = null;

var myModal = document.getElementById('createModal');

myModal.addEventListener('hide.bs.modal', function () {
    clearFields();
});

myModal.addEventListener('shown.bs.modal', function () {
    if (itemId != null) {
        let xhr = new XMLHttpRequest();
        xhr.open('GET', '/api/animal?animalId='+itemId, false);
        xhr.setRequestHeader('Content-type', 'application/json');
        xhr.send();

        if (xhr.status == 200) {
            let response = JSON.parse(xhr.responseText);
            photo.src = response.avatar;
            document.getElementById("type").value = response.type;
            document.getElementById("sex").value = response.sex;
            document.getElementById("age").value = response.age;
            document.getElementById("nickOrNumber").value = response.nickOrNumber;
            document.getElementById("breed").value = response.breed;
            document.getElementById("owner").value = response.owner;
            if (response.avatar != null && response.avatar.length != 0) {
                photo.src = response.avatar;
                document.getElementById("avatarStr").value = response.avatar;
            } else {
                photo.src = "/files/stub.png";
                document.getElementById("avatarStr").value = "/files/stub.png";
            }
        }
    } else {
        clearFields();
        hideButtons();
    }
});

uploadBtn.addEventListener("change", handleFiles, false);
function handleFiles() {
    const fileList = this.files;
    let xhr = new XMLHttpRequest();
    let formData = new FormData();
    formData.append("file", fileList[0]);
    xhr.open('POST', '/upload', false);
    xhr.send(formData);

    if (xhr.status == 200) {
        photo.src = xhr.responseText;
        document.getElementById("avatarStr").value = xhr.responseText;
    }
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
    let type = document.getElementById("type").value;
    let sex = document.getElementById("sex").value;
    let age = document.getElementById("age").value;
    let nickOrNumber = document.getElementById("nickOrNumber").value;
    let breed = document.getElementById("breed").value;
    let owner = document.getElementById("owner").value;
    let avatar = document.getElementById("avatarStr").value;

    let request = {
        "id": itemId,
        "type": type,
        "sex": sex,
        "age": age,
        "nickOrNumber": nickOrNumber,
        "breed": breed,
        "owner": owner,
        "avatar": avatar
    };

    let xhr = new XMLHttpRequest();
    xhr.open('POST', '/api/animal', false);
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
        xhr.open('DELETE', '/api/animal?animalId='+itemId, false);
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
    qrBtn.setAttribute('disabled', '');
}

function showButtons() {
    editBtn.removeAttribute('disabled');
    deleteBtn.removeAttribute('disabled');
    qrBtn.removeAttribute('disabled');
}

function clearFields() {
    photo.src = "/files/stub.png";
    document.getElementById("type").value = "";
    document.getElementById("sex").value = "";
    document.getElementById("age").value = "";
    document.getElementById("nickOrNumber").value = "";
    document.getElementById("breed").value = "";
    document.getElementById("owner").value = "";
    document.getElementById("avatar").value = "";
    document.getElementById("avatarStr").value = "/files/stub.png";
}

function qr() {
    document.getElementById("qrcode").innerHTML = "";
    new QRCode(document.getElementById("qrcode"), {
        text: location.origin + "/qr?id=" + itemId,
        width: 512,
        height: 512
    });
    const myModal = new bootstrap.Modal(document.getElementById('qrModal'), {});
    myModal.show();
}
