<template>
    <b-navbar toggleable="md" type="dark" class="bg-primary">
        <div class="jh-logo-container float-left">
            <b-navbar-toggle right class="jh-navbar-toggler d-lg-none float-right" href="javascript:void(0);"  data-toggle="collapse" target="header-tabs" aria-expanded="false" aria-label="Toggle navigation">
                <font-awesome-icon icon="bars" />
            </b-navbar-toggle>
            <b-navbar-brand class="logo float-left" b-link to="/">
                <span class="logo-img"></span>
                <span v-text="$t('global.title')" class="navbar-title">raise</span> <span class="navbar-version">{{version}}</span>
            </b-navbar-brand>
        </div>
        <b-collapse is-nav id="header-tabs">
            <b-navbar-nav class="ml-auto">
                <b-nav-item to="/" exact>
                    <span>
                        <font-awesome-icon icon="home" />
                        <span v-text="$t('global.menu.home')">Home</span>
                    </span>
                </b-nav-item>
                <b-nav-item-dropdown
                    id="entity-menu"
                    v-if="authenticated"
                    active-class="active" class="pointer">
                    <span slot="button-content" class="navbar-dropdown-menu">
                        <font-awesome-icon icon="th-list" />
                        <span v-text="$t('global.menu.entities.main')">Entities</span>
                    </span>
                    <b-dropdown-item to="/run-instance">
                        <font-awesome-icon icon="asterisk" />
                        <span v-text="$t('global.menu.entities.runInstance')">RunInstance</span>
                    </b-dropdown-item>
                    <b-dropdown-item to="/resource">
                        <font-awesome-icon icon="asterisk" />
                        <span v-text="$t('global.menu.entities.resource')">Resource</span>
                    </b-dropdown-item>
                    <b-dropdown-item to="/region">
                        <font-awesome-icon icon="asterisk" />
                        <span v-text="$t('global.menu.entities.region')">Region</span>
                    </b-dropdown-item>
                    <b-dropdown-item to="/risk-group">
                        <font-awesome-icon icon="asterisk" />
                        <span v-text="$t('global.menu.entities.riskGroup')">RiskGroup</span>
                    </b-dropdown-item>
                    <b-dropdown-item to="/supply">
                        <font-awesome-icon icon="asterisk" />
                        <span v-text="$t('global.menu.entities.supply')">Supply</span>
                    </b-dropdown-item>
                    <b-dropdown-item to="/demand">
                        <font-awesome-icon icon="asterisk" />
                        <span v-text="$t('global.menu.entities.demand')">Demand</span>
                    </b-dropdown-item>
                    <b-dropdown-item to="/odmatrix">
                        <font-awesome-icon icon="asterisk" />
                        <span v-text="$t('global.menu.entities.odmatrix')">Odmatrix</span>
                    </b-dropdown-item>
                    <b-dropdown-item to="/allocation">
                        <font-awesome-icon icon="asterisk" />
                        <span v-text="$t('global.menu.entities.allocation')">Allocation</span>
                    </b-dropdown-item>
                    <b-dropdown-item to="/infection-rate">
                        <font-awesome-icon icon="asterisk" />
                        <span v-text="$t('global.menu.entities.infectionRate')">InfectionRate</span>
                    </b-dropdown-item>
                    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
                </b-nav-item-dropdown>
                <b-nav-item-dropdown
                    id="admin-menu"
                    v-if="hasAnyAuthority('ROLE_ADMIN')"
                    :class="{'router-link-active': subIsActive('/admin')}"
                    active-class="active"
                    class="pointer">
                    <span slot="button-content" class="navbar-dropdown-menu">
                        <font-awesome-icon icon="user-plus" />
                        <span v-text="$t('global.menu.admin.main')">Administration</span>
                    </span>
                    <b-dropdown-item to="/admin/user-management">
                        <font-awesome-icon icon="user" />
                        <span v-text="$t('global.menu.admin.userManagement')">User management</span>
                    </b-dropdown-item>
                    <b-dropdown-item  to="/admin/jhi-metrics">
                        <font-awesome-icon icon="tachometer-alt" />
                        <span v-text="$t('global.menu.admin.metrics')">Metrics</span>
                    </b-dropdown-item>
                    <b-dropdown-item to="/admin/jhi-health">
                        <font-awesome-icon icon="heart" />
                        <span v-text="$t('global.menu.admin.health')">Health</span>
                    </b-dropdown-item>
                    <b-dropdown-item  to="/admin/jhi-configuration">
                        <font-awesome-icon icon="list" />
                        <span v-text="$t('global.menu.admin.configuration')">Configuration</span>
                    </b-dropdown-item>
                    <b-dropdown-item  to="/admin/audits">
                        <font-awesome-icon icon="bell" />
                        <span v-text="$t('global.menu.admin.audits')">Audits</span>
                    </b-dropdown-item>
                    <b-dropdown-item  to="/admin/logs">
                        <font-awesome-icon icon="tasks" />
                        <span v-text="$t('global.menu.admin.logs')">Logs</span>
                    </b-dropdown-item>
                    <b-dropdown-item v-if="swaggerEnabled"  to="/admin/docs">
                        <font-awesome-icon icon="book" />
                        <span v-text="$t('global.menu.admin.apidocs')">API</span>
                    </b-dropdown-item>
                    <b-dropdown-item v-if="!inProduction"  href='./h2-console' target="_tab">
                        <font-awesome-icon icon="hdd" />
                        <span v-text="$t('global.menu.admin.database')">Database</span>
                    </b-dropdown-item>
                </b-nav-item-dropdown>
                <b-nav-item-dropdown id="languagesnavBarDropdown" right v-if="languages && Object.keys(languages).length > 1">
                    <span slot="button-content">
                        <font-awesome-icon icon="flag" />
                        <span v-text="$t('global.menu.language')">Language</span>
                    </span>
                    <b-dropdown-item v-for="(value, key) in languages" :key="`lang-${key}`" v-on:click="changeLanguage(key);"
                        :class="{ active: isActiveLanguage(key)}">
                        {{value.name}}
                    </b-dropdown-item>
                </b-nav-item-dropdown>
                <b-nav-item-dropdown
                    right
                    href="javascript:void(0);"
                    id="account-menu"
                    :class="{'router-link-active': subIsActive('/account')}"
                    active-class="active"
                    class="pointer">
                    <span slot="button-content" class="navbar-dropdown-menu">
                        <font-awesome-icon icon="user" />
                        <span v-text="$t('global.menu.account.main')">
                            Account
                        </span>
                    </span>
                    <b-dropdown-item to="/account/settings" tag="b-dropdown-item" v-if="authenticated">
                        <font-awesome-icon icon="wrench" />
                        <span v-text="$t('global.menu.account.settings')">Settings</span>
                    </b-dropdown-item>
                    <b-dropdown-item to="/account/password" tag="b-dropdown-item" v-if="authenticated">
                        <font-awesome-icon icon="lock" />
                        <span v-text="$t('global.menu.account.password')">Password</span>
                    </b-dropdown-item>
                    <b-dropdown-item to="/account/sessions" tag="b-dropdown-item" v-if="authenticated">
                        <font-awesome-icon icon="cloud" />
                        <span v-text="$t('global.menu.account.sessions')">Sessions</span>
                    </b-dropdown-item>
                    <b-dropdown-item v-if="authenticated"  v-on:click="logout()" id="logout">
                        <font-awesome-icon icon="sign-out-alt" />
                        <span v-text="$t('global.menu.account.logout')">Sign out</span>
                    </b-dropdown-item>
                    <b-dropdown-item v-if="!authenticated"  v-on:click="openLogin()" id="login">
                        <font-awesome-icon icon="sign-in-alt" />
                        <span v-text="$t('global.menu.account.login')">Sign in</span>
                    </b-dropdown-item>
                    <b-dropdown-item to="/register" tag="b-dropdown-item" id="register" v-if="!authenticated">
                        <font-awesome-icon icon="user-plus" />
                        <span v-text="$t('global.menu.account.register')">Register</span>
                    </b-dropdown-item>
                </b-nav-item-dropdown>
            </b-navbar-nav>
        </b-collapse>
    </b-navbar>
</template>

<script lang="ts" src="./jhi-navbar.component.ts">
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
/* ==========================================================================
    Navbar
    ========================================================================== */
.navbar-version {
  font-size: 10px;
}


@media screen and (min-width: 768px) {
  .jh-navbar-toggler {
    display: none;
  }
}

@media screen and (min-width: 768px) and (max-width: 1150px) {
  span span{
    display:none;
  }
}

@media screen and (max-width: 767px) {
  .jh-logo-container {
    width: 100%;
  }
}

.navbar-title {
  display: inline-block;
  vertical-align: middle;
}
/* waiting for bootstrap fix bug on nav-item-dropdown a:active
https://github.com/bootstrap-vue/bootstrap-vue/issues/2219
*/
nav li.router-link-active .navbar-dropdown-menu {
  cursor: pointer;
}

/* ==========================================================================
    Logo styles
    ========================================================================== */
.navbar-brand.logo {
  padding: 5px 15px;
}

.logo .logo-img {
  height: 45px;
  display: inline-block;
  vertical-align: middle;
  width: 70px;
}

.logo-img {
  height: 100%;
  background: url("../../../content/images/logo-jhipster.png") no-repeat center
    center;
  background-size: contain;
  width: 100%;
}
</style>
