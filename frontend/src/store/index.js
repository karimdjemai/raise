import Vue from 'vue'
import Vuex from 'vuex'
import { latLng } from "leaflet";

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
      // test data
      mapFilterLocation: latLng(52.0376977, 4.32197379), //netherlands
      mapFilterLocationName: null,
      //homePageLocation: latLng(52.030433, 4.474871), //netherlands
      dataTemplateLocation: {
          name: "Rijswijk, Niederlande",
          position: latLng(52.0376977, 4.32197379),
      },
      // input table
      inpSupplyValues: [
          {
              id: 1,
              resource: null,
              position: null,
              loc_name: null,
              quantity: null,
          },
          {
              id: 2,
              resource: null,
              position: null,
              loc_name: null,
              quantity: null,
          },
          {
              id: 3,
              resource: null,
              position: null,
              loc_name: null,
              quantity: null,
          },
          {
              id: 4,
              resource: null,
              position: null,
              loc_name: null,
              quantity: null,
          },
          {
              id: 5,
              resource: null,
              position: null,
              loc_name: null,
              quantity: null,
          },
          {
              id: 6,
              resource: null,
              position: null,
              loc_name: null,
              quantity: null,
          },
      ],
      inpDemandValues: [
          {
              id: 1,
              resource: "PPE",
              position: latLng(52.060433, 4.934871),
              loc_name: "Rijswijk, Niederlande",
              quantity: 5000,
          },
          {
              id: 2,
              resource: "PPE",
              position: latLng(52.090433, 4.794871),
              loc_name: "Rijswijk, Niederlande",
              quantity: 1500,
          },
      ],
      // map output
      mapSupplyValues: [
          {
              id: 1,
              resource: "PPE",
              position: latLng(52.030433, 4.474871),
              loc_name: "Rijswijk, Niederlande",
              quantity: 2000,
          },
          {
              id: 2,
              resource: "PPE",
              position: latLng(52.040433, 4.674871),
              loc_name: "Rijswijk, Niederlande",
              quantity: 10000,
          },
      ],
      mapDemandValues: [
          {
              id: 1,
              resource: "PPE",
              position: latLng(52.060433, 4.934871),
              loc_name: "Rijswijk, Niederlande",
              quantity: 5000,
          },
          {
              id: 2,
              resource: "PPE",
              position: latLng(52.090433, 4.794871),
              loc_name: "Rijswijk, Niederlande",
              quantity: 1500,
          },
      ],
      mapAllocation: [
          {
              id: 1,
              resource: "PPE",
              from_position: latLng(52.040433, 4.674871),
              to_position: latLng(52.060433, 4.934871),
              quantity: 1500,
          },
          {
              id: 2,
              resource: "PPE",
              from_position: latLng(52.040433, 4.674871),
              to_position: latLng(52.090433, 4.794871),
              quantity: 500,
          },
      ],
      solve_button_was_pressed: false
  },
  mutations: {
        setMapFilterLocation(state, newValue) {
            state.mapFilterLocation = newValue;
        },
        setMapFilterLocationName(state, newValue) {
            state.mapFilterLocationName = newValue;
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
        solve(state) {
            state.inpSupplyValues = [
                                           {
                                               id: 1,
                                               resource: "PPE",
                                               position: latLng(52.030433, 4.474871),
                                               loc_name: "Rijswijk, Netherlands",
                                               quantity: 2000,
                                           },
                                           {
                                               id: 2,
                                               resource: "PPE",
                                               position: latLng(52.040433, 4.674871),
                                               loc_name: "Rijswijk, Netherlands",
                                               quantity: 10000,
                                           },
                                           {
                                               id: 3,
                                               resource: "PPE",
                                               position: latLng(52.040433, 4.674871),
                                               loc_name: "Amsterdam, Netherlands",
                                               quantity: 10000,
                                           },
                                           {
                                               id: 4,
                                               resource: "PPE",
                                               position: latLng(52.040433, 4.674871),
                                               loc_name: "Rijswijk, Netherlands",
                                               quantity: 200,
                                           },
                                           {
                                               id: 5,
                                               resource: "PPE",
                                               position: latLng(52.040433, 4.674871),
                                               loc_name: "Rijswijk, Netherlands",
                                               quantity: 8000,
                                           },
                                           {
                                               id: 6,
                                               resource: "PPE",
                                               position: latLng(52.040433, 4.674871),
                                               loc_name: "Rijswijk, Netherlands",
                                               quantity: 10000,
                                           },
                                       ]
        }
  },
  modules: {
  }
})
