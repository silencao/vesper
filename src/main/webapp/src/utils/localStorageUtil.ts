function set<T>(key: string, value: Object): void {
  localStorage.setItem(key, JSON.stringify(value));
}

function get(key: string): Object {
  return JSON.parse(localStorage.getItem(key));
}

export { set, get };
