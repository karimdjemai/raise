/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import AllocationDetailComponent from '@/entities/allocation/allocation-details.vue';
import AllocationClass from '@/entities/allocation/allocation-details.component';
import AllocationService from '@/entities/allocation/allocation.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Allocation Management Detail Component', () => {
    let wrapper: Wrapper<AllocationClass>;
    let comp: AllocationClass;
    let allocationServiceStub: SinonStubbedInstance<AllocationService>;

    beforeEach(() => {
      allocationServiceStub = sinon.createStubInstance<AllocationService>(AllocationService);

      wrapper = shallowMount<AllocationClass>(AllocationDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { allocationService: () => allocationServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundAllocation = { id: 123 };
        allocationServiceStub.find.resolves(foundAllocation);

        // WHEN
        comp.retrieveAllocation(123);
        await comp.$nextTick();

        // THEN
        expect(comp.allocation).toBe(foundAllocation);
      });
    });
  });
});
