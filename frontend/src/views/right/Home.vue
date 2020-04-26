<template>
	<div class="home">
		<div class="ava">
			<!--<md-avatar class="md-small" alt="test">
			
			</md-avatar-->
			<h2>Latest Updates</h2>
		</div>
		
		<md-list class="messages">
			<md-list-item class="read">
				<span class="header">25.04.2020</span>
				<span class="md-list-item-text"> Order #573 delivered successfully to location XYZ.</span>
			</md-list-item>
			<md-list-item class="read">
				<span class="header">25.04.2020</span>
				<span class="md-list-item-text">Order #573 delivered successfully to location XYZ.</span>
			</md-list-item>
		</md-list>
		
		<div class="ava">
			<md-avatar class="md-small" alt="test">
			
			</md-avatar>
			<h2>Messages</h2>
		</div>
		
		<md-list class="messages">
			<md-list-item>
				<span class="header">RE: New supplier</span>
				<span class="md-list-item-text"> Hi Adrianna, I was looking over the files you sent...</span>
			</md-list-item>
			<md-list-item class="read">
				<span class="header">25.04.2020</span>
				<span class="md-list-item-text">Order #573 delivered successfully to location XYZ.</span>
			</md-list-item>
			<md-list-item class="read">
				<span class="header">25.04.2020</span>
				<span class="md-list-item-text">Order #573 delivered successfully to location XYZ.</span>
			</md-list-item>
		</md-list>
	 
  </div>
</template>

<script>

export default {
  name: 'Home',
  components: {
  }
}
</script>
<style lang="scss">
	.home {
		.ava {
			h2 {
				color: #f2f2f2;
				font-size: 20pt;
				margin: 0
			}
			
			display: flex;
			align-items: baseline;
			
			.md-avatar {
				margin: 0.5em;
				background-color: #fff;
				display: inline;
			}
		}
		
		.messages {
			.md-list-item {
				border-radius: 1em 1em 1em 1em;
				background-color: #fff;
				margin: 0.5em;
				padding: 0.5em;
			}
			
			.read {
				background-color: #aadde0;
			}
			
			.md-list-item-content {
				display: block;
				color: #54bbc1;
				
				.header {
					font-weight: bold;
					margin-bottom: 1rem;
				}
				
				.md-list-item-text {
					font-size: 8pt;
					line-height: 2.5em;
				}
			}
			
			& + .ava {
				margin-top:2em;
			}
		}
	}
</style>
