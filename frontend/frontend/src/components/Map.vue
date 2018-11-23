<!--suppress ALL -->
<template>
   <div id="map-area">
     <h1 class="headline" id="hl1">выбери точку на карте | определи нужный радиус | получи результат</h1>
     <div id="line"
          style="top: 10.64%;
          bottom: 86.97%;">
     </div>
     <yandex-map
       id="Ymap"
       :coords="[59.9386300, 30.3141300]"
       zoom="12"
       :cluster-options="{
    1: {clusterDisableClickZoom: true}
  }"
       :behaviors="['default']"
       :controls="['fullscreenControl', 'geolocationControl', 'searchControl', 'zoomControl']"
       :placemarks="placemarks"
       map-type="map"
       @map-was-initialized
     >
       <ymap-marker
         marker-id="1"
         marker-type="placemark"
         :coords="[54.7, 39.7]"
         hint-content="Hint content 1"
         :balloon="{header: 'header', body: 'body', footer: 'footer'}"
         :icon="{color: 'green', glyph: 'cinema'}"
         cluster-name="1"
       ></ymap-marker>

       <ymap-marker
         marker-id="2"
         marker-type="placemark"
         :coords="[54.6, 39.8]"
         hint-content="Hint content 1"
         :balloon="{header: 'header', body: 'body', footer: 'footer'}"
         :icon="{color: 'green', glyph: 'cinema'}"
         cluster-name="1"
       ></ymap-marker>

       <ymap-marker
         marker-id="3"
         marker-type="circle"
         :coords="[54.62896654088406, 39.731893822753904]"
         circle-radius="1600"
         hint-content="Hint content 1"
         :marker-fill="{color: '#000000', opacity: 0.4}"
         :marker-stroke="{color: '#ffe222', width: 5}"
         :balloon="{header: 'header', body: 'body', footer: 'footer'}"
       ></ymap-marker>

     </yandex-map>
     <div id="line" style="
     top: 97.61%;
     bottom: 0%;">
     </div>

     <h1 class="headline" id="hl2">размер области поиска {{radius}}</h1>
     <div class="circle-bar">
       <div id="c1" class="circle" v-on:click="select(0.5)">
         <h2 id="r1" class="hover-bar">&#189</h2>
       </div>
       <div id="c2" class="circle" v-on:click="select(1)">
         <h2 id="r2" class="hover-bar">1 км</h2>
       </div>
       <div id="c3" class="circle" v-on:click="select(3)">
         <h2 id="r3" class="hover-bar" for="c3">3 км</h2>
       </div>
       <div id="c4" class="circle" v-on:click="select(5)">
         <h2 id="r4" class="hover-bar">5 км</h2>
       </div>
     </div>

     <h1 class="headline" id="hl2">размер области поиска {{radius}}</h1>

     <b-button class="btn">
       <span id="button-text">Анализ области</span>
     </b-button>

   </div>
</template>

<script>
  import { yandexMap, ymapMarker } from 'vue-yandex-maps'
  /*new Vue({
    components: { yandexMap, ymapMarker }
  });*/

  export default {
    name: 'map-area',
    data()
    {
      return {
        placemarks: [
          {
            coords: [54.8, 39.8],
            properties: {}, // define properties here
            options: {}, // define options here
            clusterName: "1",
            balloonTemplate: '<div>"Your custom template"</div>' ,
            callbacks: { click: function() {} }
          }
        ],
        address: '',
        value: 25,
        radius:''
      }
    },
    methods:{
      select: function (r) {
        this.radius = r
      }
    }
  }
</script>

<style scoped>
  #map-area{

    position: absolute;
    height: 752px;
    width: auto;
    left: 0px;
    right: 0px;
    top: 777px;
    background: #E5E5E5;
  }

  .headline{
    font-family: Montserrat,serif;
    font-style: normal;
    font-weight: 500;
    line-height: normal;
    font-size: 24px;
    text-align: center;

    color: #000000;
  }

  #hl1{
    position: absolute;
    left: 18.47%;
    right: 14.31%;
    top: 2.39%;
    bottom: 88.56%;
  }

  #hl2{
    position: absolute;
    left: 65.16%;
    right: 3.96%;
    top: 29.26%;
    bottom: 63.83%;
  }

  #line{
    position: absolute;
    left: 0%;
    right: 0%;
    width: auto;

    background: #F8D701;
  }

  #Ymap{
    position: absolute;
    left: 0%;
    right: 38.19%;
    top: 13.03%;
    bottom: 2.39%;
  }

  .hover-bar{
    position: relative;
    unselectable: on;
    top: 40%;
    text-align: center;
    font-size: 16px;
    display: none;
  }

  .circle{
    background: rgba(248, 215, 1, 0.46);
    -moz-border-radius: 50px;
    -webkit-border-radius: 50px;
    border: 2px solid #ffe222;
  }

  .circle:hover .hover-bar{display: block;}
  .circle:active .hover-bar{display: block;}
  .circle:active{
    background: #ffe222;
  }

  #c1{
    position: absolute;
    left: 63.07%;
    right: 34.28%;
    top: 47.47%;
    bottom: 47.47%;
    border-radius: 38px;
  }

  #c2{
    position: absolute;
    left: 69.81%;
    right: 25.88%;
    top: 45.88%;
    bottom: 45.88%;
    border-radius: 62px;
  }

  #c3{
    position: absolute;
    left: 79.35%;
    right: 14.88%;
    top: 44.41%;
    bottom: 44.55%;
    border-radius: 83px;
  }

#c4{
  position: absolute;
  left: 90.43%;
  right: 1.43%;
  top: 42.15%;
  bottom: 42.29%;
  border-radius: 117px;
}

  .btn{
    position: absolute;
    width: 331px;
    height: 88px;
    left: 69.47%;
    right: 7.51%;
    top: 71.81%;
    bottom: 16.49%;
    border: 4px solid rgba(0, 0, 0, 0.63);

    font: 24px Montserrat, serif;
    font-style: normal;
    font-weight: normal;
    line-height: normal;
    text-align: center;
    text-underline: none;

    background:transparent;
  }
  .btn:hover{
    background:#F8D701;
  }

  #button-text{
    left: 7px;
    top: 21px;

    font: 24px Montserrat, serif;
    font-style: normal;
    font-weight: normal;
    line-height: normal;
    text-align: center;
    text-underline: none;
    color: #000000;
  }
</style>
