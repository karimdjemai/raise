/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import RiskGroupDetailComponent from '@/entities/risk-group/risk-group-details.vue';
import RiskGroupClass from '@/entities/risk-group/risk-group-details.component';
import RiskGroupService from '@/entities/risk-group/risk-group.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('RiskGroup Management Detail Component', () => {
    let wrapper: Wrapper<RiskGroupClass>;
    let comp: RiskGroupClass;
    let riskGroupServiceStub: SinonStubbedInstance<RiskGroupService>;

    beforeEach(() => {
      riskGroupServiceStub = sinon.createStubInstance<RiskGroupService>(RiskGroupService);

      wrapper = shallowMount<RiskGroupClass>(RiskGroupDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { riskGroupService: () => riskGroupServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundRiskGroup = { id: 123 };
        riskGroupServiceStub.find.resolves(foundRiskGroup);

        // WHEN
        comp.retrieveRiskGroup(123);
        await comp.$nextTick();

        // THEN
        expect(comp.riskGroup).toBe(foundRiskGroup);
      });
    });
  });
});
