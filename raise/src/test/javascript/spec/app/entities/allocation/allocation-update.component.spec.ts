/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import AllocationUpdateComponent from '@/entities/allocation/allocation-update.vue';
import AllocationClass from '@/entities/allocation/allocation-update.component';
import AllocationService from '@/entities/allocation/allocation.service';

import RunInstanceService from '@/entities/run-instance/run-instance.service';

import ResourceService from '@/entities/resource/resource.service';

import RegionService from '@/entities/region/region.service';

import RiskGroupService from '@/entities/risk-group/risk-group.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Allocation Management Update Component', () => {
    let wrapper: Wrapper<AllocationClass>;
    let comp: AllocationClass;
    let allocationServiceStub: SinonStubbedInstance<AllocationService>;

    beforeEach(() => {
      allocationServiceStub = sinon.createStubInstance<AllocationService>(AllocationService);

      wrapper = shallowMount<AllocationClass>(AllocationUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          allocationService: () => allocationServiceStub,

          runInstanceService: () => new RunInstanceService(),

          resourceService: () => new ResourceService(),

          regionService: () => new RegionService(),

          riskGroupService: () => new RiskGroupService()
        }
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.allocation = entity;
        allocationServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(allocationServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.allocation = entity;
        allocationServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(allocationServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
