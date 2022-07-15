const url = '/api/user'
const urlHeader = '/api/header'
const header = document.getElementById('header')
const headerRoles = document.getElementById('headerRoles')
const tBody = document.querySelector('tbody')
let result = ''

function getAuthenticationForUserPage() {
    fetch(urlHeader)
        .then(res => res.json())
        .then(user => {
            const text = user.login
            const text2 = ' with roles: ' + user.roles.map(r => r.userRole)
            header.innerHTML = text
            headerRoles.innerHTML = text2
        })
}

getAuthenticationForUserPage()

const showTable = (user) => {
    result += `<tr>
        <td>${user.id}</td>   
        <td>${user.name}</td>
        <td>${user.surname}</td>
        <td>${user.age}</td>
        <td>${user.email}</td>
        <td>${user.login}</td>
        <td>${user.roles.map(r => r.userRole)}</td>
        </tr>`
    tBody.innerHTML = result
}

fetch(url)
    .then(response => response.json())
    .then(data => showTable(data))
    .catch(error => console.log(error))