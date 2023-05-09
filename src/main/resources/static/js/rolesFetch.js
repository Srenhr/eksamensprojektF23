console.log("rolesFetch")
const urlRoles = "http://localhost:8080/getRoles"
const ddRoles = document.getElementById("ddRoles")

function fetchAny(url) {
    console.log(url)
    return fetch(url).then((response) => response.json())
}


function fillRolesDropDown(role) {
    //console.log(kom)
    const el = document.createElement("option")
    el.textContent = role.name
    el.value = role.roleId
    el.role = role
    ddRoles.appendChild(el)
}

let roleList = []
async function fetchRegioner() {
    roleList = await fetchAny(urlRoles);
    console.log(roleList)
    roleList.forEach(fillRolesDropDown)
}

ddRoles.addEventListener('DOMContentLoaded', fetchRegioner);
