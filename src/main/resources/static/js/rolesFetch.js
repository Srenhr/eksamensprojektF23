/*
console.log("rolesFetch")
*/
const urlRoles = "http://localhost:8080/getRoles"
const ddRoles = document.getElementById("ddRoles")

/*let roleList = []

function fetchAny(url) {
    console.log(url)
    return fetch(url).then((response) => response.json())
}

async function fetchRegioner() {
    console.log("fetchRegioner")
    roleList = await fetchAny(urlRoles);
    console.log(roleList)
    roleList.forEach(fillRolesDropDown)
}

function fillRolesDropDown(role) {
    console.log("fillRolesDropdown")
    console.log(role)
    const el = document.createElement("option")
    el.textContent = role.name
    el.value = role // role.roleId
    el.role = role // backend employee currently use Set<> for roles
    ddRoles.appendChild(el)
}

fetchRegioner();
ddRoles.addEventListener('load', fetchRegioner);*/

window.addEventListener('load', populateDDRoles)

function populateDDRoles() {
    console.log("vi er i populateDDRoles")
    fetchRoles()
        .then((response) => {
            console.log(response)
            response.forEach(buildDD)
        })
}

async function fetchRoles() {
    console.log("vi er i fetch roles")
    return await fetch(urlRoles)
        .then(response => response.json())
        .catch(err => console.error(err));
}

function buildDD(role) {
    console.log("vi er i buildDD")
    const option = document.createElement("option");
    console.log("role: " + JSON.stringify(role))
/*
    option.value = role.name;
*/
    option.value = JSON.stringify(role);
    let oldStr = role.name;
    console.log(oldStr)
    let newStr = oldStr.replace("ROLE_", "");
    console.log(newStr)
    option.textContent = newStr;
    ddRoles.appendChild(option);
}

document.addEventListener('DOMContentLoaded', function () {
    const formEmployee = document.getElementById('formEmployee');

    formEmployee.addEventListener('submit', function (event) {
        event.preventDefault(); // prevent the default form submission

        // create an object containing the form data
        const selectedOptions = ddRoles.selectedOptions;

        const values = [];
        for (let i = 0; i < selectedOptions.length; i++) {
            values.push(selectedOptions[i].value);
        }

        const formData = {
            username: formEmployee.elements.username.value,
            password: formEmployee.elements.password.value,
            firstName: formEmployee.elements.firstName.value,
            lastName: formEmployee.elements.lastName.value,
            phoneNumber: formEmployee.elements.phoneNumber.value,
            roles: values
        };

        // send a fetch request to the server
        fetch('http://localhost:8080/admin/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
            .then(response => {
                // handle the response from the server
            })
            .catch(error => {
                // handle any errors that occur during the request
            });
    });
});



