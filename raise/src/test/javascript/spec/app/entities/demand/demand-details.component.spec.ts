/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import DemandDetailComponent from '@/entities/demand/demand-details.vue';
import DemandClass from '@/entities/demand/demand-details.component';
import DemandService from '@/entities/demand/demand.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Demand Management Detail Component', () => {
    let wrapper: Wrapper<DemandClass>;
    let comp: DemandClass;
    let demandServiceStub: SinonStubbedInstance<DemandService>;

    beforeEach(() => {
      demandServiceStub = sinon.createStubInstance<DemandService>(DemandService);

      wrapper = shallowMount<DemandClass>(DemandDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { demandService: () => demandServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundDemand = { id: 123 };
        demandServiceStub.find.resolves(foundDemand);

        // WHEN
        comp.retrieveDemand(123);
        await comp.$nextTick();

        // THEN
        expect(comp.demand).toBe(foundDemand);
      });
    });
  });
});
