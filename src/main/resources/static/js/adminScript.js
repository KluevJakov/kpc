let editBtn = document.getElementById("edit");
let deleteBtn = document.getElementById("delete");
var itemId = null;

var myModal = document.getElementById('createModal');

myModal.addEventListener('hide.bs.modal', function () {
    document.getElementById("name").value = "";
});

myModal.addEventListener('shown.bs.modal', function () {
    if (itemId != null) {
        let xhr = new XMLHttpRequest();
        xhr.open('GET', '/api/user?userId='+itemId, false);
        xhr.setRequestHeader('Content-type', 'application/json');
        xhr.send();

        if (xhr.status == 200) {
            let response = JSON.parse(xhr.responseText);
            document.getElementById("name").value = response.name;
        }
    } else {
        document.getElementById("name").value = "";
    }
});

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
    let name = document.getElementById("name").value;
    let request = {
        "id": itemId,
        "name": name
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