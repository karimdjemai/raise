/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import InfectionRateUpdateComponent from '@/entities/infection-rate/infection-rate-update.vue';
import InfectionRateClass from '@/entities/infection-rate/infection-rate-update.component';
import InfectionRateService from '@/entities/infection-rate/infection-rate.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('InfectionRate Management Update Component', () => {
    let wrapper: Wrapper<InfectionRateClass>;
    let comp: InfectionRateClass;
    let infectionRateServiceStub: SinonStubbedInstance<InfectionRateService>;

    beforeEach(() => {
      infectionRateServiceStub = sinon.createStubInstance<InfectionRateService>(InfectionRateService);

      wrapper = shallowMount<InfectionRateClass>(InfectionRateUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          infectionRateService: () => infectionRateServiceStub
        }
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.infectionRate = entity;
        infectionRateServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(infectionRateServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.infectionRate = entity;
        infectionRateServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(infectionRateServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
