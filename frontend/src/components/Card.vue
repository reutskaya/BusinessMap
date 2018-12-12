<template>
  <div id="card">
    <img v-bind:src="createImagePath" height="325" width="440"/>
    <h4 @click="toggle"><span>{{ category.name }}</span></h4>
    <transition name="fade">
      <div class="rectangle" v-if="visible"><div class="dot" @click="toggle"><img src="../assets/close.jpg"
                                                                                  height="30" width="30" alt="../assets/background404"/></div><br><br>
        <div id="text" v-bind:key="type.name" v-for="type in category.types">
          <h5>Тип бизнеса: {{ type.name }}, количество: {{ type.col }}, средний рейтинг: {{ type.reit }}, средний чек: {{ type.price }}.</h5></div></div><br>
    </transition>
  </div>
</template>
<script>
  export default {
    name : "Card",
    props: {
      category: null
    },
    data(){
      return{
        visible: false
      }
    },
    methods: {
      toggle: function () {
        this.visible = !this.visible
      }
    },
    computed: {
      createImagePath: function () {
        return `dist/${this.category.name}.jpg`;
      }
    }
  }
</script>
<style>
  .fade-enter-active, .fade-leave-active {
    transition: opacity .5s;
  }
  .fade-enter, .fade-leave-to /* .fade-leave-active до версии 2.1.8 */ {
    opacity: 0;
  }
  #card{
    position: relative;
    width: 440px;
    height: 325px;
    margin: 15px;
    float:left;
  }
  .rectangle {
    position: relative;
    top: -325px;
    width: 440px;
    height: 325px;
    background: #C4C4C4;
    z-index: 100;
    border-width: 5px;
  }
  .dot {
    position: absolute;
    height: 25px;
    width: 25px;
    background-color: #bbb;
    border-radius: 50%;
    left: 400px;
    top: 5px;
  }
  .dot::selection {
    background: transparent;
  }
  h4 {
    position	: absolute;
    top		: 250px;
    left		: 0;
    bottom:0;
    width		: 100%;
    box-sizing:border-box;
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    color:#fff;
    padding:10px;
    background-color:rgba(0,0,0,.3);
  }
  h4 span {
    font			: bold 24px/45px Helvetica, Sans-Serif;
    letter-spacing		: -1px;
  }
  h4:hover {
    color: #ffe222; /* Цвет текста */
    background: #fff; /* Цвет фона */
  }
  h5{
    margin: 10px;
    text-align: left;
    font-size: 15px;
  }
</style>
