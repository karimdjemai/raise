/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import RiskGroupUpdateComponent from '@/entities/risk-group/risk-group-update.vue';
import RiskGroupClass from '@/entities/risk-group/risk-group-update.component';
import RiskGroupService from '@/entities/risk-group/risk-group.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('RiskGroup Management Update Component', () => {
    let wrapper: Wrapper<RiskGroupClass>;
    let comp: RiskGroupClass;
    let riskGroupServiceStub: SinonStubbedInstance<RiskGroupService>;

    beforeEach(() => {
      riskGroupServiceStub = sinon.createStubInstance<RiskGroupService>(RiskGroupService);

      wrapper = shallowMount<RiskGroupClass>(RiskGroupUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          riskGroupService: () => riskGroupServiceStub
        }
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.riskGroup = entity;
        riskGroupServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(riskGroupServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.riskGroup = entity;
        riskGroupServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(riskGroupServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
