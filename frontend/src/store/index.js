import Vue from 'vue'
import Vuex from 'vuex'
import { latLng } from "leaflet";

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
      graph_before: true,
      
      notifications: [
          {
              header: '25.04.2020',
              text: 'Order #573 delivered successfully to location XYZ.',
              read: true
          },
          {
              header: '25.04.2020',
              text: 'Order #573 delivered successfully to location XYZ.',
              read: true
          }
      ],
      // MAP
      mapFilterResource: "PPE",
      mapFilterView: "",
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
          {
            id: 3,
            resource: "Ventilators",
            position: latLng(51.9244201, 4.4777326),
            loc_name: "Rotterdam, Niederlande",
            quantity: 5,
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
          {
            id: 3,
            resource: "Ventilators",
            position: latLng(52.090433, 4.794871),
            loc_name: "Rijswijk, Niederlande",
            quantity: 10,
          },
      ],
      //TODO visualize flow of resources = allocation
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
        setMapFilterResource(state, newValue) {
            state.mapFilterResource = newValue;
        },
        setMapFilterView(state, newValue) {
            state.mapFilterView = newValue;
        },
        notifs(state) {
            state.notifications = [
                                                                 {
                                                                     header: '25.04.2020',
                                                                     text: '126 Ventilators moved from Gelderland to Groningen',
                                                                     read: false
                                                                 },
                                                                 {
                                                                     header: '25.04.2020',
                                                                     text: '15 Ventilators moved from Drenthe to Friesland',
                                                                     read: false
                                                                 }
                                                             ].concat(state.notifications)
        },
      
        graph(state) {
            state.graph_before = !state.graph_before
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
        },
      
        changeBubbles(state) {
            state.mapDemandValues[0].quantity = 3000
            state.mapDemandValues[1].quantity = 4000
            state.mapSupplyValues[0].quantity = 4000
            state.mapSupplyValues[1].quantity = 6000
        }
  },
  modules: {
  }
})
