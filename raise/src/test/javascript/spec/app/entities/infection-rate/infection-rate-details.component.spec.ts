/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import InfectionRateDetailComponent from '@/entities/infection-rate/infection-rate-details.vue';
import InfectionRateClass from '@/entities/infection-rate/infection-rate-details.component';
import InfectionRateService from '@/entities/infection-rate/infection-rate.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('InfectionRate Management Detail Component', () => {
    let wrapper: Wrapper<InfectionRateClass>;
    let comp: InfectionRateClass;
    let infectionRateServiceStub: SinonStubbedInstance<InfectionRateService>;

    beforeEach(() => {
      infectionRateServiceStub = sinon.createStubInstance<InfectionRateService>(InfectionRateService);

      wrapper = shallowMount<InfectionRateClass>(InfectionRateDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { infectionRateService: () => infectionRateServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundInfectionRate = { id: 123 };
        infectionRateServiceStub.find.resolves(foundInfectionRate);

        // WHEN
        comp.retrieveInfectionRate(123);
        await comp.$nextTick();

        // THEN
        expect(comp.infectionRate).toBe(foundInfectionRate);
      });
    });
  });
});
