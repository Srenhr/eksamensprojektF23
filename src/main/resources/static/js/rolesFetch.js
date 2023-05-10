console.log("rolesFetch")
const urlRoles = "http://localhost:8080/getRoles"
const ddRoles = document.getElementById("ddRoles")

let roleList = []

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
ddRoles.addEventListener('load', fetchRegioner);
