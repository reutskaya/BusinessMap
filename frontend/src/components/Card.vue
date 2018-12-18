<template>
  <div id="card">
    <img v-bind:src="createImagePath" height="325" width="440"/>
    <h4 @click="toggle">
      <span>{{ category.name }}
      <div id="circleC" :class="[{ 'colorRed': category.quality === 1 },
         { 'colorOrange': category.quality === 2 }, { 'colorGreen': category.quality === 3 },
         { 'sizeBig': category.reitCol === 3 }, { 'sizeMedium': category.reitCol === 2 },
         { 'sizeSmall': category.reitCol === 1 }]">
      </div>
    </span>
    </h4>
    <transition name="fade">
      <div class="rectangle" v-if="visible">
        <div class="dot" @click="toggle">
          <img src="../assets/close.jpg" height="30" width="30"/></div>
        <br>
        <div id="text" v-bind:key="type.name" v-for="type in category.types">
          <h5><p>{{ type.name }} ({{ type.col }})</p>
            <p class="body">средний рейтинг: {{ type.reit }}</p>
            <p class="body">средний чек: {{ type.price }}</p>
          </h5>
          <h6>{{ category.verdict }}</h6></div><br>
        </div>
    </transition>
  </div>
</template>
<script>
  export default {
    name: "Card",
    props: {
      category: null
    },
    data() {
      return {
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
      },
      sizeAndColor: function () {
        return `[colorRed, sizeBig]`;
      }
    }
  }
</script>
<style>
  .fade-enter-active, .fade-leave-active {
    transition: opacity .5s;
  }

  .fade-enter, .fade-leave-to /* .fade-leave-active до версии 2.1.8 */
  {
    opacity: 0;
  }

  #card {
    position: relative;
    width: 440px;
    height: 325px;
    margin: 15px;
    float: left;
  }

  .rectangle {
    position: relative;
    top: -325px;
    width: 440px;
    height: 325px;
    background: #E5E5E5;
    z-index: 100;
    border: 1px ridge black;
  }

  #circleC {
    position: absolute;
    border-radius: 50%;
    z-index: 80;
    left: 350px;
    top: 50%;
    transform: translate(0, -50%);
  }
  .colorRed{
    background-color: red;
  }
  .colorGreen{
    background-color: green;
  }
  .colorOrange{
    background-color: orange;
  }
  .sizeBig{
    height: 80px;
    width: 80px;
  }
  .sizeMedium{
    height: 55px;
    width: 55px;
  }
  .sizeSmall{
    height: 30px;
    width: 30px;
  }

  .dot {
    position: absolute;
    height: 30px;
    width: 30px;
    background-color: #E5E5E5;
    border-radius: 50%;
    left: 400px;
    top: 5px;
  }

  .dot::selection {
    background: transparent;
  }

  h4 {
    position: absolute;
    top: 250px;
    left: 0;
    bottom: 0;
    width: 100%;
    box-sizing: border-box;
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    color: #fff;
    padding: 10px;
    background-color: rgba(0, 0, 0, .3);
    text-align: left;
  }

  h4 span {
    font: 24px/48px Montserrat;
    font-style: normal;
    font-weight: 600;
  }

  h4:hover {
    color: #000; /* Цвет текста */
    background: rgba(229, 229, 229, .7);; /* Цвет фона */
  }

  h5 {
    margin: 30px;
    text-align: left;
    font-size: 15px;
  }

  h6{
    position: absolute;
    margin: 30px;
    text-align: left;
    font-size: 15px;
    bottom: 5px;
  }

  p {
    margin: 0px;
  }

  p.body {
    text-indent: 5em;
  }

  .rectangle {
    background-color: rgba(0, 0, 0, .7);
    color: aliceblue;
    font-family: Montserrat, serif;
    font-style: normal;
  }
</style>
