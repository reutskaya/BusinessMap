<template lang="html">
  <div class="notfound">
    <div class="block">
    <h2>404!</h2>

    <h2>Ты зашел слишком далеко..</h2>

    <h2>Такой страницы пока нет :с </h2>
    <div>
      <router-link :to="{ path: '/' }"><b-button size="lg" variant="warning">на главную</b-button></router-link>
    </div>
    </div>
  </div>
</template>

<script>
export default {
}
</script>

<style lang="css">
.notfound {
  margin: 0;
  height: 850px;
  background-image: url("../assets/background404.jpg");
  background-size: cover;
}

.block {
    width: 250px;
    height: 250px;
    position: absolute;
    top: 50%;
    left: 50%;
    margin: -125px 0 0 -125px;
}
</style>
