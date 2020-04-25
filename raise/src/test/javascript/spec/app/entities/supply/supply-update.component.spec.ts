/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import SupplyUpdateComponent from '@/entities/supply/supply-update.vue';
import SupplyClass from '@/entities/supply/supply-update.component';
import SupplyService from '@/entities/supply/supply.service';

import RunInstanceService from '@/entities/run-instance/run-instance.service';

import ResourceService from '@/entities/resource/resource.service';

import RegionService from '@/entities/region/region.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Supply Management Update Component', () => {
    let wrapper: Wrapper<SupplyClass>;
    let comp: SupplyClass;
    let supplyServiceStub: SinonStubbedInstance<SupplyService>;

    beforeEach(() => {
      supplyServiceStub = sinon.createStubInstance<SupplyService>(SupplyService);

      wrapper = shallowMount<SupplyClass>(SupplyUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          supplyService: () => supplyServiceStub,

          runInstanceService: () => new RunInstanceService(),

          resourceService: () => new ResourceService(),

          regionService: () => new RegionService()
        }
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.supply = entity;
        supplyServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(supplyServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.supply = entity;
        supplyServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(supplyServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
