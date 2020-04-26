import Vue from 'vue'
import Vuex from 'vuex'
import { latLng } from "leaflet";

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    // test data
    mapFilterLocation: latLng(52.030433, 4.474871), //netherlands
    //homePageLocation: latLng(52.030433, 4.474871), //netherlands
    dataTemplateLocation: {name: "Rijswijk, Niederlande", position: latLng(52.0376977, 4.32197379)},            
    // input table
    inpSupplyValues: [{id: 1, resource: "PPE", 
                          position: latLng(52.030433, 4.474871), 
                          loc_name: "Rijswijk, Niederlande", 
                          quantity: 2000}, 
                      {id: 2, resource: "PPE", 
                          position: latLng(52.040433, 4.674871), 
                          loc_name: "Rijswijk, Niederlande", quantity: 10000}],
    inpDemandValues: [{id: 1, resource: "PPE", 
                          position: latLng(52.060433, 4.934871), 
                          loc_name: "Rijswijk, Niederlande", quantity: 5000}, 
                      {id: 2, resource: "PPE", 
                          position: latLng(52.090433, 4.794871), 
                          loc_name: "Rijswijk, Niederlande", quantity: 1500}],
     // map output
    mapSupplyValues: [{id: 1, resource: "PPE", 
                        position: latLng(52.030433, 4.474871), 
                        loc_name: "Rijswijk, Niederlande", 
                        quantity: 2000}, 
                    {id: 2, resource: "PPE", 
                        position: latLng(52.040433, 4.674871), 
                        loc_name: "Rijswijk, Niederlande", quantity: 10000}],
    mapDemandValues: [{id: 1, resource: "PPE", 
                        position: latLng(52.060433, 4.934871), 
                        loc_name: "Rijswijk, Niederlande", quantity: 5000}, 
                    {id: 2, resource: "PPE", 
                        position: latLng(52.090433, 4.794871), 
                        loc_name: "Rijswijk, Niederlande", quantity: 1500}] ,   
    mapAllocation: [{id: 1, resource: "PPE", 
                        from_position: latLng(52.040433, 4.674871), 
                        to_position: latLng(52.060433, 4.934871), 
                        quantity: 1500}, 
                        {id: 2, resource: "PPE", 
                        from_position: latLng(52.040433, 4.674871), 
                        to_position: latLng(52.090433, 4.794871), 
                        quantity: 500}
                    ] ,   
  },
  mutations: {
    setMapFilterLocation(state, newValue) {
      state.mapFilterLocation = newValue;
    },
    setHomePageLocation(state, newValue) {
      state.mapFilterLocation = newValue;
    },
    setDataTemplateLocation(state, newValue) {
      state.dataTemplateLocation = newValue;
    },
    setInpSupplyValues(state, newValue) {
      state.inpSupplyValues = newValue;
    },
    setInpDemandValues(state, newValue) {
      state.inpDemandValues = newValue;
    },
  },
  modules: {
  }
})
