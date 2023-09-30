let editBtn = document.getElementById("edit");
let deleteBtn = document.getElementById("delete");
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
        xhr.open('GET', '/api/book?bookId='+itemId, false);
        xhr.setRequestHeader('Content-type', 'application/json');
        xhr.send();

        if (xhr.status == 200) {
            let response = JSON.parse(xhr.responseText);
            photo.src = response.avatar;
            document.getElementById("title").value = response.title;
            document.getElementById("type").value = response.type;
            document.getElementById("author").value = response.author;
            document.getElementById("publishYear").value = response.publishYear;
            document.getElementById("text").value = response.text;
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
    let title = document.getElementById("title").value;
    let type = document.getElementById("type").value;
    let author = document.getElementById("author").value;
    let publishYear = document.getElementById("publishYear").value;
    let text = document.getElementById("text").value;
    let avatar = document.getElementById("avatarStr").value;

    let request = {
        "id": itemId,
        "title": title,
        "type": type,
        "author": author,
        "publishYear": publishYear,
        "text": text,
        "avatar": avatar
    };

    let xhr = new XMLHttpRequest();
    xhr.open('POST', '/api/book', false);
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
        xhr.open('DELETE', '/api/book?bookId='+itemId, false);
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
    photo.src = "/files/stub.png";
    document.getElementById("title").value = "";
    document.getElementById("type").value = "";
    document.getElementById("author").value = "";
    document.getElementById("publishYear").value = "";
    document.getElementById("text").value = "";
    document.getElementById("avatar").value = "";
    document.getElementById("avatarStr").value = "/files/stub.png";
}