import {defineConfig} from 'vite';
import vue from '@vitejs/plugin-vue';

const paths = {
    vue: 'https://unpkg.com/vue@3.1.1/dist/vue.runtime.esm-browser.prod.js'
}

export default defineConfig({
    plugins: [vue()],
    resolve: {
        alias: {
        }
    },
    server: {
        proxy: {
            '^/api': {
                target: 'http://localhost',
                changeOrigin: true,
                configure(proxy) {
                    proxy.on('error', (err, req, res) => {
                        res.statusCode = 500;
                        res.end(err.message)
                    })
                }
            }
        }
    },
    build: {
        rollupOptions: {
            output: {paths},
            external: Object.keys(paths)

        }
    }
});
