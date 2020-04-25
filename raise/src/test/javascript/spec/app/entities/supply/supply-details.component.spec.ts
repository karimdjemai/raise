/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import SupplyDetailComponent from '@/entities/supply/supply-details.vue';
import SupplyClass from '@/entities/supply/supply-details.component';
import SupplyService from '@/entities/supply/supply.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Supply Management Detail Component', () => {
    let wrapper: Wrapper<SupplyClass>;
    let comp: SupplyClass;
    let supplyServiceStub: SinonStubbedInstance<SupplyService>;

    beforeEach(() => {
      supplyServiceStub = sinon.createStubInstance<SupplyService>(SupplyService);

      wrapper = shallowMount<SupplyClass>(SupplyDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { supplyService: () => supplyServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundSupply = { id: 123 };
        supplyServiceStub.find.resolves(foundSupply);

        // WHEN
        comp.retrieveSupply(123);
        await comp.$nextTick();

        // THEN
        expect(comp.supply).toBe(foundSupply);
      });
    });
  });
});
