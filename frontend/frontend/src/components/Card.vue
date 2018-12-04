<template>
  <div id="card">
    <div id="с1" class="rectangle"><img src="../assets/Food.jpg" height="325" width="440"/>
      <h2 @click="toggle"><span>ЕДА</span></h2>
      <transition name="fade">
      <div class = "message" v-if="visible">Я анализ</div>
    </transition>
    </div>
    <div id="с2" class="rectangle"><img src="../assets/Entertainment.jpg" height="325" width="440"/> <h2><span>РАЗВЛЕЧЕНИЯ</span></h2></div>
    <div id="с3" class="rectangle"><img src="../assets/Hotel.jpg" height="325" width="440"/> <h2><span>ГОСТИНИЦЫ</span></h2></div>
    <div id="с4" class="rectangle"><img src="../assets/Store.jpg" height="325" width="440"/> <h2><span>ПОКУПКИ</span></h2></div>
    <div id="с5" class="rectangle"><img src="../assets/Beauty.jpg" height="325" width="440"/> <h2><span>КРАСОТА</span></h2></div>
    <div id="с6" class="rectangle"><img src="../assets/Health.jpg" height="325" width="440"/> <h2><span>ЗДОРОВЬЕ</span></h2></div>
  </div>
</template>

<script>
export default {
  data(){
    return{
      visible: false
    }
  },
  methods: {
    toggle: function () {
      this.visible = !this.visible
    }
  }
}
</script>

<style>
  .fade-enter-active, .fade-leave-active {
    transition: opacity .5s;
  }
  .fade-enter, .fade-leave-active /* .fade-leave-active до версии 2.1.8 */ {
    opacity: 0;
    transform: translateX(20px);
  }

  #card{
    position: absolute;
    height: 1500px;
    width: auto;
    left: 0px;
    right: 0px;
    top: 1500px;
  }

  .rectangle {
    width: 440px;
    height: 325px;
    background: #4da1f7;
  }
  #с1{
    position: absolute;
    left: 5px;
    top: 30px;
    bottom: 355px;
  }
  #с2{
    position: absolute;
    left: 452px;
    top: 30px;
    bottom: 355px;
  }
  #с3{
    position: absolute;
    left: 900px;
    top: 30px;
    bottom: 355px;
  }
  #с4{
    position: absolute;
    left: 5px;
    top: 370px;
    bottom: 30px;
  }
  #с5{
    position: absolute;
    left: 452px;
    top: 370px;
    bottom: 30px;
  }
  #с6{
    position: absolute;
    left: 900px;
    top: 370px;
    bottom: 30px;
  }

  h2 {
    position	: absolute;
    top		: 250px;
    left		: 0;
    bottom:0;
    width		: 100%;
    box-sizing:border-box;
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    color:#fff;
    padding:10px;
    background-color:rgba(0,0,0,.3);
  }

  h2 span {
    font			: bold 24px/45px Helvetica, Sans-Serif;
    letter-spacing		: -1px;
  }

  h2:hover {
    color: #ffe222; /* Цвет текста */
    background: #fff; /* Цвет фона */
  }

</style>
