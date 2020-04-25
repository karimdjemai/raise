/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import OdmatrixDetailComponent from '@/entities/odmatrix/odmatrix-details.vue';
import OdmatrixClass from '@/entities/odmatrix/odmatrix-details.component';
import OdmatrixService from '@/entities/odmatrix/odmatrix.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Odmatrix Management Detail Component', () => {
    let wrapper: Wrapper<OdmatrixClass>;
    let comp: OdmatrixClass;
    let odmatrixServiceStub: SinonStubbedInstance<OdmatrixService>;

    beforeEach(() => {
      odmatrixServiceStub = sinon.createStubInstance<OdmatrixService>(OdmatrixService);

      wrapper = shallowMount<OdmatrixClass>(OdmatrixDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { odmatrixService: () => odmatrixServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundOdmatrix = { id: 123 };
        odmatrixServiceStub.find.resolves(foundOdmatrix);

        // WHEN
        comp.retrieveOdmatrix(123);
        await comp.$nextTick();

        // THEN
        expect(comp.odmatrix).toBe(foundOdmatrix);
      });
    });
  });
});
