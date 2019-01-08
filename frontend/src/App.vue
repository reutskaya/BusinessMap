<template>
    <div id="app">
        <router-view/>
    </div>
</template>

<script>
    import router from './router'
    export default {
        name: 'app',
        router
    }
</script>
<!-- стили компонента -->
<style>
    #app {
        font-family: 'Merriweather', serif, 'Montserrat', sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        margin-top: 0;
        background: #E5E5E5;
        background-size: cover;
    }
</style>