/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import RunInstanceUpdateComponent from '@/entities/run-instance/run-instance-update.vue';
import RunInstanceClass from '@/entities/run-instance/run-instance-update.component';
import RunInstanceService from '@/entities/run-instance/run-instance.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('RunInstance Management Update Component', () => {
    let wrapper: Wrapper<RunInstanceClass>;
    let comp: RunInstanceClass;
    let runInstanceServiceStub: SinonStubbedInstance<RunInstanceService>;

    beforeEach(() => {
      runInstanceServiceStub = sinon.createStubInstance<RunInstanceService>(RunInstanceService);

      wrapper = shallowMount<RunInstanceClass>(RunInstanceUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          runInstanceService: () => runInstanceServiceStub
        }
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.runInstance = entity;
        runInstanceServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(runInstanceServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.runInstance = entity;
        runInstanceServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(runInstanceServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
