function set(key, value) {
    localStorage.setItem(key, JSON.stringify(value));
}
function get(key) {
    return JSON.parse(localStorage.getItem(key));
}
export { set, get };
