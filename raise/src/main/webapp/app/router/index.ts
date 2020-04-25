import Vue from 'vue';
import Component from 'vue-class-component';
Component.registerHooks([
  'beforeRouteEnter',
  'beforeRouteLeave',
  'beforeRouteUpdate' // for vue-router 2.2+
]);
import Router from 'vue-router';
import { Authority } from '@/shared/security/authority';
const Home = () => import('../core/home/home.vue');
const Error = () => import('../core/error/error.vue');
const Register = () => import('../account/register/register.vue');
const Activate = () => import('../account/activate/activate.vue');
const ResetPasswordInit = () => import('../account/reset-password/init/reset-password-init.vue');
const ResetPasswordFinish = () => import('../account/reset-password/finish/reset-password-finish.vue');
const ChangePassword = () => import('../account/change-password/change-password.vue');
const Settings = () => import('../account/settings/settings.vue');
const JhiUserManagementComponent = () => import('../admin/user-management/user-management.vue');
const JhiUserManagementViewComponent = () => import('../admin/user-management/user-management-view.vue');
const JhiUserManagementEditComponent = () => import('../admin/user-management/user-management-edit.vue');
const Sessions = () => import('../account/sessions/sessions.vue');
const JhiConfigurationComponent = () => import('../admin/configuration/configuration.vue');
const JhiDocsComponent = () => import('../admin/docs/docs.vue');
const JhiHealthComponent = () => import('../admin/health/health.vue');
const JhiLogsComponent = () => import('../admin/logs/logs.vue');
const JhiAuditsComponent = () => import('../admin/audits/audits.vue');
const JhiMetricsComponent = () => import('../admin/metrics/metrics.vue');
/* tslint:disable */
// prettier-ignore
const RunInstance = () => import('../entities/run-instance/run-instance.vue');
// prettier-ignore
const RunInstanceUpdate = () => import('../entities/run-instance/run-instance-update.vue');
// prettier-ignore
const RunInstanceDetails = () => import('../entities/run-instance/run-instance-details.vue');
// prettier-ignore
const Resource = () => import('../entities/resource/resource.vue');
// prettier-ignore
const ResourceUpdate = () => import('../entities/resource/resource-update.vue');
// prettier-ignore
const ResourceDetails = () => import('../entities/resource/resource-details.vue');
// prettier-ignore
const Region = () => import('../entities/region/region.vue');
// prettier-ignore
const RegionUpdate = () => import('../entities/region/region-update.vue');
// prettier-ignore
const RegionDetails = () => import('../entities/region/region-details.vue');
// prettier-ignore
const RiskGroup = () => import('../entities/risk-group/risk-group.vue');
// prettier-ignore
const RiskGroupUpdate = () => import('../entities/risk-group/risk-group-update.vue');
// prettier-ignore
const RiskGroupDetails = () => import('../entities/risk-group/risk-group-details.vue');
// prettier-ignore
const Supply = () => import('../entities/supply/supply.vue');
// prettier-ignore
const SupplyUpdate = () => import('../entities/supply/supply-update.vue');
// prettier-ignore
const SupplyDetails = () => import('../entities/supply/supply-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

Vue.use(Router);

// prettier-ignore
export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/forbidden',
      name: 'Forbidden',
      component: Error,
      meta: { error403: true }
    },
    {
      path: '/not-found',
      name: 'NotFound',
      component: Error,
      meta: { error404: true }
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/account/activate',
      name: 'Activate',
      component: Activate
    },
    {
      path: '/account/reset/request',
      name: 'ResetPasswordInit',
      component: ResetPasswordInit
    },
    {
      path: '/account/reset/finish',
      name: 'ResetPasswordFinish',
      component: ResetPasswordFinish
    },
    {
      path: '/account/password',
      name: 'ChangePassword',
      component: ChangePassword,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/account/sessions',
      name: 'Sessions',
      component: Sessions,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/account/settings',
      name: 'Settings',
      component: Settings,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/admin/user-management',
      name: 'JhiUser',
      component: JhiUserManagementComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/user-management/new',
      name: 'JhiUserCreate',
      component: JhiUserManagementEditComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/user-management/:userId/edit',
      name: 'JhiUserEdit',
      component: JhiUserManagementEditComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/user-management/:userId/view',
      name: 'JhiUserView',
      component: JhiUserManagementViewComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/docs',
      name: 'JhiDocsComponent',
      component: JhiDocsComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/audits',
      name: 'JhiAuditsComponent',
      component: JhiAuditsComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/jhi-health',
      name: 'JhiHealthComponent',
      component: JhiHealthComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/logs',
      name: 'JhiLogsComponent',
      component: JhiLogsComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/jhi-metrics',
      name: 'JhiMetricsComponent',
      component: JhiMetricsComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/jhi-configuration',
      name: 'JhiConfigurationComponent',
      component: JhiConfigurationComponent,
      meta: { authorities: [Authority.ADMIN] }
    }
    ,
    {
      path: '/run-instance',
      name: 'RunInstance',
      component: RunInstance,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/run-instance/new',
      name: 'RunInstanceCreate',
      component: RunInstanceUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/run-instance/:runInstanceId/edit',
      name: 'RunInstanceEdit',
      component: RunInstanceUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/run-instance/:runInstanceId/view',
      name: 'RunInstanceView',
      component: RunInstanceDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/resource',
      name: 'Resource',
      component: Resource,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/resource/new',
      name: 'ResourceCreate',
      component: ResourceUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/resource/:resourceId/edit',
      name: 'ResourceEdit',
      component: ResourceUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/resource/:resourceId/view',
      name: 'ResourceView',
      component: ResourceDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/region',
      name: 'Region',
      component: Region,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/region/new',
      name: 'RegionCreate',
      component: RegionUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/region/:regionId/edit',
      name: 'RegionEdit',
      component: RegionUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/region/:regionId/view',
      name: 'RegionView',
      component: RegionDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/risk-group',
      name: 'RiskGroup',
      component: RiskGroup,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/risk-group/new',
      name: 'RiskGroupCreate',
      component: RiskGroupUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/risk-group/:riskGroupId/edit',
      name: 'RiskGroupEdit',
      component: RiskGroupUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/risk-group/:riskGroupId/view',
      name: 'RiskGroupView',
      component: RiskGroupDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/supply',
      name: 'Supply',
      component: Supply,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/supply/new',
      name: 'SupplyCreate',
      component: SupplyUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/supply/:supplyId/edit',
      name: 'SupplyEdit',
      component: SupplyUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/supply/:supplyId/view',
      name: 'SupplyView',
      component: SupplyDetails,
      meta: { authorities: [Authority.USER] }
    }
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ]
});
