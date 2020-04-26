import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    mapFilterLocation: [52.030433, 4.474871],
    dataTemplateLocation: "Rijswijk",
  },
  mutations: {
    setMapFilterLocation(state, newValue) {
      console.log(state);
      console.log(newValue);
      console.log(state.mapFilterLocation);
      state.mapFilterLocation = newValue;
    },
    setdataTemplateLocation(state, newValue) {
      state.dataTemplateLocation = newValue;
    }
  },
  modules: {
  }
})
