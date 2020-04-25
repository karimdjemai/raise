/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import RunInstanceDetailComponent from '@/entities/run-instance/run-instance-details.vue';
import RunInstanceClass from '@/entities/run-instance/run-instance-details.component';
import RunInstanceService from '@/entities/run-instance/run-instance.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('RunInstance Management Detail Component', () => {
    let wrapper: Wrapper<RunInstanceClass>;
    let comp: RunInstanceClass;
    let runInstanceServiceStub: SinonStubbedInstance<RunInstanceService>;

    beforeEach(() => {
      runInstanceServiceStub = sinon.createStubInstance<RunInstanceService>(RunInstanceService);

      wrapper = shallowMount<RunInstanceClass>(RunInstanceDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { runInstanceService: () => runInstanceServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundRunInstance = { id: 123 };
        runInstanceServiceStub.find.resolves(foundRunInstance);

        // WHEN
        comp.retrieveRunInstance(123);
        await comp.$nextTick();

        // THEN
        expect(comp.runInstance).toBe(foundRunInstance);
      });
    });
  });
});
