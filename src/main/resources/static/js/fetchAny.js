function fetchAny(url) {
    console.log(url)
    return fetch(url).then((response) => response.json())
}