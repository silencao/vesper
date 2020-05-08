function set<T>(key: string, value: Record<string, T>): void {
  localStorage.setItem(key, JSON.stringify(value));
}

function get(key: string): Record<string, any> {
  return JSON.parse(localStorage.getItem(key) || '');
}

export { set, get };
